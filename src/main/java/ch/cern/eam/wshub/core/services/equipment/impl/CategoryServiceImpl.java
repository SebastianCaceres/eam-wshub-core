package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.equipment.CategoryService;
import ch.cern.eam.wshub.core.services.equipment.entities.Category;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import ch.cern.eam.wshub.core.repositories.CategoryRepository;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {

    private Tools tools;

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(Tools tools) {
    }

    public CategoryServiceImpl(Tools tools, CategoryRepository categoryRepository) {
        this.tools = tools;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category readCategory(InforContext context, String categoryCode) throws InforException {
        return categoryRepository.findById(categoryCode).orElse(null);
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
