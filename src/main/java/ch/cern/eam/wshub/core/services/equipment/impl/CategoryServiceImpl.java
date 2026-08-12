package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.equipment.CategoryService;
import ch.cern.eam.wshub.core.services.equipment.entities.Category;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import net.datastream.schemas.mp_entities.equipmentcategory_001.EquipmentCategory;
import net.datastream.schemas.mp_fields.CATEGORYID;
import net.datastream.schemas.mp_functions.mp0323_001.MP0323_AddEquipmentCategory_001;
import net.datastream.schemas.mp_functions.mp0324_001.MP0324_GetEquipmentCategory_001;
import net.datastream.schemas.mp_functions.mp0325_001.MP0325_SyncEquipmentCategory_001;
import net.datastream.schemas.mp_functions.mp0326_001.MP0326_DeleteEquipmentCategory_001;
import net.datastream.schemas.mp_results.mp0323_001.MP0323_AddEquipmentCategory_001_Result;
import net.datastream.schemas.mp_results.mp0324_001.MP0324_GetEquipmentCategory_001_Result;
import net.datastream.schemas.mp_results.mp0325_001.MP0325_SyncEquipmentCategory_001_Result;
import net.datastream.wsdls.inforws.InforWebServicesPT;
import ch.cern.eam.wshub.core.repositories.CategoryRepository;
import java.util.Optional;
import static ch.cern.eam.wshub.core.tools.DataTypeTools.toCodeString;

public class CategoryServiceImpl implements CategoryService {

    private Tools tools;

    private InforWebServicesPT inforws;

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(Tools tools, InforWebServicesPT inforws) {
        this(tools, inforws, null);
    }

    public CategoryServiceImpl(Tools tools, InforWebServicesPT inforws, CategoryRepository categoryRepository) {
        this.tools = tools;
        this.inforws = inforws;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category readCategory(InforContext context, String categoryCode) throws InforException {
        return categoryRepository.findById(categoryCode).orElse(null);
    }

    private EquipmentCategory readInforCategory(InforContext context, String categoryCode) throws InforException {
        MP0324_GetEquipmentCategory_001 getEquipmentCategory001 = new MP0324_GetEquipmentCategory_001();
        getEquipmentCategory001.setCATEGORYID(new CATEGORYID());
        getEquipmentCategory001.getCATEGORYID().setCATEGORYCODE(categoryCode);
        MP0324_GetEquipmentCategory_001_Result result = tools.performInforOperation(context, inforws::getEquipmentCategoryOp, getEquipmentCategory001);
        return result.getResultData().getEquipmentCategory();
    }

    @Override
    public String updateCategory(InforContext context, Category category) throws InforException {
        Category saved = categoryRepository.save(category);
        return saved.getCode();
    }

    @Override
    public String createCategory(InforContext context, Category category) throws InforException {
        Category saved = categoryRepository.save(category);
        return saved.getCode();
    }

    @Override
    public String deleteCategory(InforContext context, String categoryCode) throws InforException {
        categoryRepository.deleteById(categoryCode);
        return categoryCode;
    }
}
