package ch.cern.eam.wshub.core.client;

import ch.cern.eam.wshub.core.interceptors.InforInterceptor;
import ch.cern.eam.wshub.core.interceptors.InforInvocationHandler;
import ch.cern.eam.wshub.core.repositories.*;
import ch.cern.eam.wshub.core.services.administration.DataspyService;
import ch.cern.eam.wshub.core.services.administration.ScreenLayoutService;
import ch.cern.eam.wshub.core.services.administration.UserGroupMenuService;
import ch.cern.eam.wshub.core.services.administration.UserSetupService;
import ch.cern.eam.wshub.core.services.administration.impl.DataspyServiceImpl;
import ch.cern.eam.wshub.core.services.administration.impl.ScreenLayoutServiceImpl;
import ch.cern.eam.wshub.core.services.administration.impl.UserGroupMenuServiceImpl;
import ch.cern.eam.wshub.core.services.administration.impl.UserSetupServiceImpl;
import ch.cern.eam.wshub.core.services.casemanagement.CaseManagementService;
import ch.cern.eam.wshub.core.services.casemanagement.impl.CaseManagementServiceImpl;
import ch.cern.eam.wshub.core.services.comments.CommentService;
import ch.cern.eam.wshub.core.services.comments.impl.CommentServiceImpl;
import ch.cern.eam.wshub.core.services.contractmanagement.EquipmentReservationAdjustmentService;
import ch.cern.eam.wshub.core.services.contractmanagement.impl.EquipmentReservationAdjustmentServiceImpl;
import ch.cern.eam.wshub.core.services.documents.DocumentsService;
import ch.cern.eam.wshub.core.services.documents.impl.DocumentsServiceImpl;
import ch.cern.eam.wshub.core.services.equipment.*;
import ch.cern.eam.wshub.core.services.equipment.impl.*;
import ch.cern.eam.wshub.core.services.grids.GridsService;
import ch.cern.eam.wshub.core.services.grids.impl.GridsServiceImpl;
import ch.cern.eam.wshub.core.services.material.*;
import ch.cern.eam.wshub.core.services.material.impl.*;
import ch.cern.eam.wshub.core.services.userdefinedscreens.UserDefinedListService;
import ch.cern.eam.wshub.core.services.userdefinedscreens.UserDefinedScreenService;
import ch.cern.eam.wshub.core.services.userdefinedscreens.UserDefinedTableService;
import ch.cern.eam.wshub.core.services.userdefinedscreens.impl.UserDefinedListServiceImpl;
import ch.cern.eam.wshub.core.services.userdefinedscreens.impl.UserDefinedScreenServiceImpl;
import ch.cern.eam.wshub.core.services.userdefinedscreens.impl.UserDefinedTableServiceImpl;
import ch.cern.eam.wshub.core.services.workorders.*;
import ch.cern.eam.wshub.core.services.workorders.impl.*;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.CacheKey;
import ch.cern.eam.wshub.core.tools.Tools;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.Serializable;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Client for Infor services
 * This class is thread-safe. On single instance can and should be reused to handle multiple calls,
 * even if it involves different Infor users.
 */
@Getter
public class InforClient implements Serializable {

    public static Map<CacheKey, Cache<String, Object>> cacheMap = new ConcurrentHashMap<>();

    private Tools tools;

    private CommentService commentService;
    private WorkOrderService workOrderService;
    private StandardWorkOrderService standardWorkOrderService;
    @Setter
    private StandardWorkOrderChildService standardWorkOrderChildService;
    private CaseService caseService;
    private CaseTaskService caseTaskService;
    private LaborBookingService laborBookingService;
    private WorkOrderMiscService workOrderMiscService;
    private EmployeeService employeeService;
    private ChecklistService checklistService;
    private InspectionService inspectionService;
    private RouteService routeService;
    private TaskPlanService taskPlanService;
    private SalesPriceService salesPriceService;

    private AssetService assetService;
    private PositionService positionService;
    private SystemService systemService;
    private LocationService locationService;
    private EquipmentFacadeService equipmentFacadeService;

    private EquipmentStructureService equipmentStructureService;
    private LinearReferenceService linearReferenceService;
    private PMScheduleService pmScheduleService;
    private EquipmentWarrantyCoverageService equipmentWarrantyCoverageService;
    private EquipmentOtherService equipmentOtherService;

    private PartService partService;
    private PartKitService partKitService;
    private PartMiscService partMiscService;
    private PartStoreService partStoreService;
    private PartManufacturerService partManufacturerService;
    private PartBinStockService partBinStockService;
    private PartLotService partLotService;
    private PurchaseOrdersService purchaseOrdersService;
    private PickTicketService pickTicketService;
    private PhysicalInventoryService physicalInventoryService;

    private UserSetupService userSetupService;
    private GridsService gridsService;
    private DocumentsService documentsService;
    private DataspyService dataspyService;
    private UserGroupMenuService userGroupMenuService;
    private ScreenLayoutService screenLayoutService;

    private EquipmentGenerationService equipmentGenerationService;
    private EquipmentConfigurationService equipmentConfigurationService;

    private UserDefinedTableService userDefinedTableServices;
    private UserDefinedListService userDefinedListService;
    private UserDefinedScreenService userDefinedScreenService;

    private MECService mecService;

    private SafetyService safetyService;
    private CategoryService categoryService;

    private EquipmentReservationService equipmentReservationService;

    private EquipmentMeterReadingService equipmentMeterReadingService;

    private NonconformityService nonconformityService;
    private NonConformityObservationService nonConformityObservationService;

    private NonPoReceiptService nonPoReceiptService;
    private NonPoReceiptPartService nonPoReceiptPartService;

    private Store2StoreTransferService store2StoreTransferService;

    @Setter
    private CaseManagementService caseManagementService;

    @Setter
    private EquipmentReservationAdjustmentService equipmentReservationAdjustmentService;

    // Prevent initializing the class without the builder
    private InforClient() {}

    public static class Builder {
        private String url;
        private String tenant;
        private String defaultOrganizationCode;
        private InforInterceptor inforInterceptor;
        private ExecutorService executorService;
        private DataSource dataSource;
        private EntityManagerFactory entityManagerFactory;
        private Logger logger;
        private Boolean withJPAGridsAuthentication = false;
        private WorkOrderRepository workOrderRepository;
        private ActivityRepository activityRepository;
        private LaborBookingRepository laborBookingRepository;
        private FindingRepository findingRepository;
        private PartRepository partRepository;
        private EquipmentRepository equipmentRepository;
        private EmployeeRepository employeeRepository;
        private CategoryRepository categoryRepository;
        private InforDocumentRepository inforDocumentRepository;
        private InforDocEntityRepository inforDocEntityRepository;
        private InforCaseRepository inforCaseRepository;
        private InforCaseTaskRepository inforCaseTaskRepository;
        private EquipmentPMScheduleRepository equipmentPMScheduleRepository;
        private EquipmentWarrantyRepository equipmentWarrantyRepository;
        private EAMUserRepository eamUserRepository;
        private DataspyCustomFieldRepository dataspyCustomFieldRepository;
        private DataspyFieldRepository dataspyFieldRepository;
        private EquipmentDepreciationRepository equipmentDepreciationRepository;
        private GridDataspyRepository gridDataspyRepository;
        private GridFieldRepository gridFieldRepository;
        private GridMetadataRequestResultRepository gridMetadataRequestResultRepository;
        private InstallParametersRepository installParametersRepository;
        private PartAssociationRepository partAssociationRepository;
        private PhysicalInventoryRepository physicalInventoryRepository;
        private PhysicalInventoryRowRepository physicalInventoryRowRepository;
        private RouteEquipmentRepository routeEquipmentRepository;
        private RouteRepository routeRepository;
        private Store2StoreTransferDTORepository store2StoreTransferDTORepository;
        private StoreTransactionPartLineRepository storeTransactionPartLineRepository;
        private CommentRepository commentRepository;
        private StandardWorkOrderRepository standardWorkOrderRepository;
        private TaskPlanRepository taskPlanRepository;
        private LocationRepository locationRepository;
        private NonConformityRepository nonConformityRepository;
        private LotRepository lotRepository;
        private PickTicketRepository pickTicketRepository;

        private Boolean localizeResults = true;
        private Map<CacheKey, Cache<String, Object>> cacheMap = InforClient.cacheMap;

        public Builder(String url) {
            this.url = url;
        }

        public Builder withDefaultTenant(String defaultTenant) {
            this.tenant = defaultTenant;
            return this;
        }

        public Builder withDefaultOrganizationCode(String defaultOrganizationCode) {
            this.defaultOrganizationCode = defaultOrganizationCode;
            return this;
        }

        public Builder withInforInterceptor(InforInterceptor inforInterceptor) {
            this.inforInterceptor = inforInterceptor;
            return this;
        }

        public Builder withExecutorService(ExecutorService executorService) {
            this.executorService = executorService;
            return this;
        }

        public Builder withDataSource(DataSource dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public Builder withEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
            this.entityManagerFactory = entityManagerFactory;
            return this;
        }

        public Builder withLogger(Logger logger) {
            this.logger = logger;
            return this;
        }
        
        public Builder withJPAGridsAuthentication() {
        	this.withJPAGridsAuthentication = true;
        	return this;
        }

        public Builder withCache(Map<CacheKey, Cache<String, Object>> cacheMap) {
            this.cacheMap = cacheMap;
            return this;
        }

        public Builder localizeResults(Boolean localizeResults) {
            this.localizeResults = localizeResults;
            return this;
        }

        public Builder withWorkOrderRepository(WorkOrderRepository workOrderRepository) {
            this.workOrderRepository = workOrderRepository;
            return this;
        }

        public Builder withActivityRepository(ActivityRepository activityRepository) {
            this.activityRepository = activityRepository;
            return this;
        }

        public Builder withLaborBookingRepository(LaborBookingRepository laborBookingRepository) {
            this.laborBookingRepository = laborBookingRepository;
            return this;
        }

        public Builder withFindingRepository(FindingRepository findingRepository) {
            this.findingRepository = findingRepository;
            return this;
        }

        public Builder withPartRepository(PartRepository partRepository) {
            this.partRepository = partRepository;
            return this;
        }

        public Builder withEquipmentRepository(EquipmentRepository equipmentRepository) {
            this.equipmentRepository = equipmentRepository;
            return this;
        }

        public Builder withEmployeeRepository(EmployeeRepository employeeRepository) {
            this.employeeRepository = employeeRepository;
            return this;
        }

        public Builder withCategoryRepository(CategoryRepository categoryRepository) {
            this.categoryRepository = categoryRepository;
            return this;
        }

        public Builder withInforDocumentRepository(InforDocumentRepository inforDocumentRepository) {
            this.inforDocumentRepository = inforDocumentRepository;
            return this;
        }

        public Builder withInforDocEntityRepository(InforDocEntityRepository inforDocEntityRepository) {
            this.inforDocEntityRepository = inforDocEntityRepository;
            return this;
        }

        public Builder withInforCaseRepository(InforCaseRepository inforCaseRepository) {
            this.inforCaseRepository = inforCaseRepository;
            return this;
        }

        public Builder withInforCaseTaskRepository(InforCaseTaskRepository inforCaseTaskRepository) {
            this.inforCaseTaskRepository = inforCaseTaskRepository;
            return this;
        }

        public Builder withEquipmentPMScheduleRepository(EquipmentPMScheduleRepository equipmentPMScheduleRepository) {
            this.equipmentPMScheduleRepository = equipmentPMScheduleRepository;
            return this;
        }

        public Builder withEquipmentWarrantyRepository(EquipmentWarrantyRepository equipmentWarrantyRepository) {
            this.equipmentWarrantyRepository = equipmentWarrantyRepository;
            return this;
        }

        public Builder withEAMUserRepository(EAMUserRepository eamUserRepository) {
            this.eamUserRepository = eamUserRepository;
            return this;
        }

        public Builder withDataspyCustomFieldRepository(DataspyCustomFieldRepository dataspyCustomFieldRepository) {
            this.dataspyCustomFieldRepository = dataspyCustomFieldRepository;
            return this;
        }

        public Builder withDataspyFieldRepository(DataspyFieldRepository dataspyFieldRepository) {
            this.dataspyFieldRepository = dataspyFieldRepository;
            return this;
        }

        public Builder withEquipmentDepreciationRepository(EquipmentDepreciationRepository equipmentDepreciationRepository) {
            this.equipmentDepreciationRepository = equipmentDepreciationRepository;
            return this;
        }

        public Builder withGridDataspyRepository(GridDataspyRepository gridDataspyRepository) {
            this.gridDataspyRepository = gridDataspyRepository;
            return this;
        }

        public Builder withGridFieldRepository(GridFieldRepository gridFieldRepository) {
            this.gridFieldRepository = gridFieldRepository;
            return this;
        }

        public Builder withGridMetadataRequestResultRepository(GridMetadataRequestResultRepository gridMetadataRequestResultRepository) {
            this.gridMetadataRequestResultRepository = gridMetadataRequestResultRepository;
            return this;
        }

        public Builder withInstallParametersRepository(InstallParametersRepository installParametersRepository) {
            this.installParametersRepository = installParametersRepository;
            return this;
        }

        public Builder withPartAssociationRepository(PartAssociationRepository partAssociationRepository) {
            this.partAssociationRepository = partAssociationRepository;
            return this;
        }

        public Builder withPhysicalInventoryRepository(PhysicalInventoryRepository physicalInventoryRepository) {
            this.physicalInventoryRepository = physicalInventoryRepository;
            return this;
        }

        public Builder withPhysicalInventoryRowRepository(PhysicalInventoryRowRepository physicalInventoryRowRepository) {
            this.physicalInventoryRowRepository = physicalInventoryRowRepository;
            return this;
        }

        public Builder withRouteEquipmentRepository(RouteEquipmentRepository routeEquipmentRepository) {
            this.routeEquipmentRepository = routeEquipmentRepository;
            return this;
        }

        public Builder withRouteRepository(RouteRepository routeRepository) {
            this.routeRepository = routeRepository;
            return this;
        }

        public Builder withStore2StoreTransferDTORepository(Store2StoreTransferDTORepository store2StoreTransferDTORepository) {
            this.store2StoreTransferDTORepository = store2StoreTransferDTORepository;
            return this;
        }

        public Builder withStoreTransactionPartLineRepository(StoreTransactionPartLineRepository storeTransactionPartLineRepository) {
            this.storeTransactionPartLineRepository = storeTransactionPartLineRepository;
            return this;
        }

        public Builder withCommentRepository(CommentRepository commentRepository) {
            this.commentRepository = commentRepository;
            return this;
        }

        public Builder withStandardWorkOrderRepository(StandardWorkOrderRepository standardWorkOrderRepository) {
            this.standardWorkOrderRepository = standardWorkOrderRepository;
            return this;
        }

        public Builder withTaskPlanRepository(TaskPlanRepository taskPlanRepository) {
            this.taskPlanRepository = taskPlanRepository;
            return this;
        }

        public Builder withLocationRepository(LocationRepository locationRepository) {
            this.locationRepository = locationRepository;
            return this;
        }

        public Builder withNonConformityRepository(NonConformityRepository nonConformityRepository) {
            this.nonConformityRepository = nonConformityRepository;
            return this;
        }

        public Builder withLotRepository(LotRepository lotRepository) {
            this.lotRepository = lotRepository;
            return this;
        }

        public Builder withPickTicketRepository(PickTicketRepository pickTicketRepository) {
            this.pickTicketRepository = pickTicketRepository;
            return this;
        }

        private <T> T proxy(Class<T> targetClass, T target, InforInterceptor inforInterceptor, Tools tools) {
            return (T) Proxy.newProxyInstance(targetClass.getClassLoader(), new Class[] { targetClass }, new InforInvocationHandler<>(target, inforInterceptor, tools));
        }

        public InforClient build() {
            InforClient inforClient = new InforClient();

            // Application Data
            ApplicationData applicationData = new ApplicationData();
            applicationData.setUrl(this.url);
            applicationData.setOrganization(this.defaultOrganizationCode);
            applicationData.setTenant(this.tenant);
            applicationData.setWithJPAGridsAuthentication(withJPAGridsAuthentication);
            ApplicationData.localizeResults = localizeResults;

            InforClient.cacheMap = this.cacheMap;

            // Tools
            Tools tools = new Tools(applicationData,
                    this.executorService,
                    this.dataSource,
                    this.entityManagerFactory,
                    this.logger);
            inforClient.tools = tools;

            //
            // Init Service Classes
            //
            inforClient.workOrderService = proxy(WorkOrderService.class, new WorkOrderServiceImpl(applicationData, tools, workOrderRepository), inforInterceptor, tools);
            inforClient.standardWorkOrderService = proxy(StandardWorkOrderService.class, new StandardWorkOrderServiceImpl(applicationData, tools, standardWorkOrderRepository), inforInterceptor, tools);
            inforClient.standardWorkOrderChildService = proxy(StandardWorkOrderChildService.class, new StandardWorkOrderChildServiceImpl(applicationData, tools), inforInterceptor, tools);
            inforClient.commentService = proxy(CommentService.class, new CommentServiceImpl(applicationData, tools, commentRepository), inforInterceptor, tools);
            inforClient.caseService = proxy(CaseService.class, new CaseServiceImpl(applicationData, tools, inforCaseRepository), inforInterceptor, tools);
            inforClient.caseTaskService = proxy(CaseTaskService.class, new CaseTaskServiceImpl(applicationData, tools, inforCaseTaskRepository), inforInterceptor, tools);
            inforClient.checklistService = proxy(ChecklistService.class, new ChecklistServiceImpl(applicationData, tools, findingRepository), inforInterceptor, tools);
            inforClient.inspectionService = proxy(InspectionService.class, new InspectionServiceImpl(applicationData, tools), inforInterceptor, tools);
            inforClient.laborBookingService = proxy(LaborBookingService.class, new LaborBookingServiceImpl(applicationData, tools, laborBookingRepository, activityRepository), inforInterceptor, tools);
            inforClient.workOrderMiscService = proxy(WorkOrderMiscService.class, new WorkOrderMiscServiceImpl(applicationData, tools), inforInterceptor, tools);
            inforClient.employeeService = proxy(EmployeeService.class, new EmployeeServiceImpl(applicationData, tools, employeeRepository), inforInterceptor, tools);
            inforClient.categoryService = proxy(CategoryService.class, new CategoryServiceImpl(tools, categoryRepository), inforInterceptor, tools);
            inforClient.assetService = proxy(AssetService.class, new AssetServiceImpl(applicationData, tools, equipmentRepository), inforInterceptor, tools);
            inforClient.positionService = proxy(PositionService.class, new PositionServiceImpl(applicationData, tools, equipmentRepository), inforInterceptor, tools);
            inforClient.systemService = proxy(SystemService.class, new SystemServiceImpl(applicationData, tools, equipmentRepository), inforInterceptor, tools);
            inforClient.equipmentFacadeService = proxy(EquipmentFacadeService.class, new EquipmentFacadeServiceImpl(applicationData, tools), inforInterceptor, tools);
            inforClient.equipmentStructureService = proxy(EquipmentStructureService.class, new EquipmentStructureServiceImpl(applicationData, tools), inforInterceptor, tools);
            inforClient.linearReferenceService = proxy(LinearReferenceService.class, new LinearReferenceServiceImpl(applicationData, tools), inforInterceptor, tools);
            inforClient.pmScheduleService = proxy(PMScheduleService.class, new PMScheduleServiceImpl(applicationData, tools, equipmentPMScheduleRepository), inforInterceptor, tools);
            inforClient.equipmentWarrantyCoverageService = proxy(EquipmentWarrantyCoverageService.class, new EquipmentWarrantyCoverageServiceImpl(applicationData, tools, equipmentWarrantyRepository), inforInterceptor, tools);
            inforClient.equipmentOtherService = proxy(EquipmentOtherService.class, new EquipmentOtherServiceImpl(applicationData, tools, equipmentDepreciationRepository), inforInterceptor, tools);
            inforClient.partService = proxy(PartService.class, new PartServiceImpl(applicationData, tools, partRepository), inforInterceptor, tools);
            inforClient.partMiscService = proxy(PartMiscService.class, new PartMiscServiceImpl(applicationData, tools, partAssociationRepository), inforInterceptor, tools);
            inforClient.partStoreService = proxy(PartStoreService.class, new PartStoreServiceImpl(applicationData, tools), inforInterceptor, tools);
            inforClient.partManufacturerService = proxy(PartManufacturerService.class, new PartManufacturerServiceImpl(applicationData, tools), inforInterceptor, tools);
            inforClient.partBinStockService = proxy(PartBinStockService.class, new PartBinStockServiceImpl(applicationData, tools), inforInterceptor, tools);
            inforClient.partLotService = proxy(PartLotService.class, new PartLotServiceImpl(applicationData, tools, lotRepository), inforInterceptor, tools);
            inforClient.locationService = proxy(LocationService.class, new LocationServiceImpl(applicationData, tools, locationRepository), inforInterceptor, tools);
            inforClient.partKitService = proxy(PartKitService.class, new PartKitServiceImpl(applicationData, tools), inforInterceptor, tools);
            inforClient.purchaseOrdersService = proxy(PurchaseOrdersService.class, new PurchaseOrdersImpl(applicationData, tools), inforInterceptor, tools);
            inforClient.userSetupService = proxy(UserSetupService.class, new UserSetupServiceImpl(applicationData, tools, eamUserRepository), inforInterceptor, tools);
            inforClient.gridsService = proxy(GridsService.class, new GridsServiceImpl(applicationData, tools, gridDataspyRepository, gridFieldRepository, gridMetadataRequestResultRepository, installParametersRepository), inforInterceptor, tools);
            inforClient.documentsService = proxy(DocumentsService.class, new DocumentsServiceImpl(applicationData, tools, inforDocumentRepository, inforDocEntityRepository), inforInterceptor, tools);
            inforClient.pickTicketService = proxy(PickTicketService.class, new PickTicketServiceImpl(applicationData, tools, pickTicketRepository), inforInterceptor, tools);
            inforClient.physicalInventoryService = proxy(PhysicalInventoryService.class, new PhysicalInventoryServiceImpl(applicationData, tools, physicalInventoryRepository, physicalInventoryRowRepository), inforInterceptor, tools);
            inforClient.equipmentGenerationService = proxy(EquipmentGenerationService.class, new EquipmentGenerationServiceImpl(applicationData, tools),inforInterceptor, tools);
            inforClient.equipmentConfigurationService = proxy(EquipmentConfigurationService.class, new EquipmentConfigurationServiceImpl(applicationData, tools),inforInterceptor, tools);
            inforClient.dataspyService = proxy(DataspyService.class, new DataspyServiceImpl(applicationData, tools, dataspyCustomFieldRepository, dataspyFieldRepository),inforInterceptor, tools);
            inforClient.userGroupMenuService = proxy(UserGroupMenuService.class, new UserGroupMenuServiceImpl(applicationData, tools), inforInterceptor, tools);
            inforClient.screenLayoutService = proxy(ScreenLayoutService.class, new ScreenLayoutServiceImpl(applicationData, tools), inforInterceptor, tools);
            inforClient.userDefinedTableServices = proxy(UserDefinedTableService.class, new UserDefinedTableServiceImpl(applicationData, tools), inforInterceptor, tools);
            inforClient.userDefinedListService = proxy(UserDefinedListService.class, new UserDefinedListServiceImpl(applicationData, tools), inforInterceptor, tools);
            inforClient.routeService = proxy(RouteService.class, new RouteServiceImpl(applicationData, tools, routeEquipmentRepository, routeRepository), inforInterceptor, tools);
            inforClient.mecService = proxy(MECService.class, new MECServiceImpl(applicationData, tools, workOrderRepository), inforInterceptor, tools);

            inforClient.safetyService = proxy(SafetyService.class, new SafetyServiceImpl(applicationData, tools), inforInterceptor, tools);
            inforClient.taskPlanService = proxy(TaskPlanService.class, new TaskPlanServiceImpl(applicationData, tools, taskPlanRepository), inforInterceptor, tools);
            inforClient.salesPriceService = proxy(SalesPriceService.class, new SalesPricesImpl(applicationData, tools), inforInterceptor, tools);
            inforClient.userDefinedScreenService = proxy(UserDefinedScreenService.class, new UserDefinedScreenServiceImpl(applicationData, tools), inforInterceptor, tools);
            inforClient.equipmentReservationService = proxy(EquipmentReservationService.class, new EquipmentReservationServiceImpl(applicationData, tools),inforInterceptor, tools);
            inforClient.nonconformityService = proxy(NonconformityService.class, new NonconformityServiceImpl(applicationData, tools, nonConformityRepository), inforInterceptor, tools);
            inforClient.nonConformityObservationService = proxy(NonConformityObservationService.class, new NonConformityObservationServiceImpl(applicationData, tools, findingRepository), inforInterceptor, tools);
            inforClient.nonPoReceiptPartService = proxy(NonPoReceiptPartService.class, new NonPoReceiptPartServiceImpl(applicationData, tools), inforInterceptor, tools);
            inforClient.nonPoReceiptService = proxy(NonPoReceiptService.class, new NonPoReceiptServiceImpl(applicationData, tools), inforInterceptor, tools);

            inforClient.store2StoreTransferService = proxy(Store2StoreTransferService.class, new Store2StoreTransferServiceImpl(
                    applicationData,
                    tools,
                    store2StoreTransferDTORepository,
                    storeTransactionPartLineRepository), inforInterceptor, tools);
            inforClient.caseManagementService = proxy(CaseManagementService.class, new CaseManagementServiceImpl(
                    applicationData,
                    tools), inforInterceptor, tools);
            inforClient.equipmentMeterReadingService = proxy(EquipmentMeterReadingService.class,
                    new EquipmentMeterReadingServiceImpl(applicationData, tools),
                    inforInterceptor, tools);
            inforClient.equipmentReservationAdjustmentService = proxy(EquipmentReservationAdjustmentService.class, new EquipmentReservationAdjustmentServiceImpl(applicationData, tools), inforInterceptor, tools);
            if (!tools.isDatabaseConnectionConfigured()) {
                logger.log(Level.WARNING, "Some of the services might require a database connection.");
            }
            return inforClient;
        }

    }
}
