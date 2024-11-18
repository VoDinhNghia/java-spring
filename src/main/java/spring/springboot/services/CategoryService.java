package spring.springboot.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.springboot.entities.CategoryEntity;
import spring.springboot.repositories.CategoryRepository;
import spring.springboot.utils.QueryList;
import spring.springboot.constants.Constants;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository cateRepo;

    QueryList queryList = new QueryList();

    public CategoryEntity createCategory(CategoryEntity cateEntity) {
        CategoryEntity result = this.cateRepo.save(cateEntity);
        return result;
    }

    public Boolean updateCategory(CategoryEntity cateEntity) {
        this.cateRepo.save(cateEntity);
        return true;
    }

    public Map<String, Object> listCategories(String limit, String page, String searchKey) {
        List<CategoryEntity> findAll;
        long total = this.cateRepo.findAll().stream().count();
        long skip = this.queryList.calPagination(total, limit, page).get(Constants.querySkip);
        long numberLimit = this.queryList.calPagination(total, limit, page).get(Constants.numberLimit);
        if (searchKey != null) {
            findAll = this.cateRepo.search(searchKey);
        } else {
            findAll = this.cateRepo.findAll();
        }
        List<CategoryEntity> results = findAll.stream().skip(skip).limit(numberLimit).collect(Collectors.toList());
        return this.queryList.resList(results, total);
    }

    public Boolean deleteCategory(UUID id) {
        this.cateRepo.deleteById(id);
        return true;
    }

    public Optional<CategoryEntity> findById(UUID id) {
        Optional<CategoryEntity> result = this.cateRepo.findById(id);
        return result;
    }
}
