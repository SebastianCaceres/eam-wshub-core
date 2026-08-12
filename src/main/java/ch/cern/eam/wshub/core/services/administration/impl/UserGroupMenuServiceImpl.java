package ch.cern.eam.wshub.core.services.administration.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.administration.UserGroupMenuService;
import ch.cern.eam.wshub.core.services.administration.entities.MenuEntryNode;
import ch.cern.eam.wshub.core.services.administration.entities.MenuRequestType;
import ch.cern.eam.wshub.core.services.administration.entities.MenuSpecification;
import ch.cern.eam.wshub.core.services.entities.BatchResponse;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import java.util.ArrayList;
import java.util.List;

public class UserGroupMenuServiceImpl implements UserGroupMenuService {

    private Tools tools;
    private ApplicationData applicationData;

    public UserGroupMenuServiceImpl(ApplicationData applicationData, Tools tools) {
        this.applicationData = applicationData;
        this.tools = tools;
    }

    @Override
    public String addToMenuHierarchy(InforContext context, MenuSpecification menuSpecification) throws InforException {
        return "OK";
    }

    @Override
    public BatchResponse<String> addToMenuHierarchyBatch(InforContext context, List<MenuSpecification> menuSpecificationList) {
        return tools.batchOperation(context, this::addToMenuHierarchy, menuSpecificationList);
    }

    @Override
    public BatchResponse<String> addToMenuHierarchyManyUsergroups(InforContext context, List<String> userGroups, MenuSpecification menuSpecification) {
        List<MenuSpecification> menuSpecificationList = new ArrayList<>();
        userGroups.forEach(u -> menuSpecificationList.add(new MenuSpecification(menuSpecification.getMenuPath(), menuSpecification.getFunctionCode(), u)));
        return addToMenuHierarchyBatch(context, menuSpecificationList);
    }

    @Override
    public BatchResponse<String> deleteFromMenuHierarchyBatch(InforContext context, List<MenuSpecification> menuSpecificationList) {
        return tools.batchOperation(context, this::deleteFromMenuHierarchy, menuSpecificationList);
    }

    @Override
    public BatchResponse<String> deleteFromMenuHierarchyManyUsergroups(InforContext context, List<String> userGroups, MenuSpecification menuSpecification) {
        List<MenuSpecification> menuSpecificationList = new ArrayList<>();
        userGroups.forEach(u -> menuSpecificationList.add(new MenuSpecification(menuSpecification.getMenuPath(), menuSpecification.getFunctionCode(), u)));
        return deleteFromMenuHierarchyBatch(context, menuSpecificationList);
    }

    public MenuEntryNode getExtMenuHierarchyAsTree(InforContext context, String userGroup, MenuRequestType requestType) throws InforException {
        return null;
    }

    @Override
    public String deleteFromMenuHierarchy(InforContext context, MenuSpecification menuSpecification) throws InforException {
        return "OK";
    }
}
