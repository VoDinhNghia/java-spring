package spring.springboot.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.springboot.entities.CategoryEntity;
import spring.springboot.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository cateRepo;

    public CategoryEntity createCategory(CategoryEntity cateEntity) {
        CategoryEntity result = cateRepo.save(cateEntity);
        return result;
    }

    public Boolean updateCategory(CategoryEntity cateEntity) {
        cateRepo.save(cateEntity);
        return true;
    }

    public Map<String, Object> listCategories(String limit, String page, String searchKey) {
        List<CategoryEntity> findAll;
        long total = cateRepo.findAll().stream().count();
        long skip = 0;
        long numberLimit = total;
        if (limit != null && page != null) {
            numberLimit = Integer.parseInt(limit);
            skip = (Integer.parseInt(page) - 1) * numberLimit;
        }
        if (searchKey != null) {
            findAll = cateRepo.search(searchKey);
        } else {
            findAll = cateRepo.findAll();
        }
        List<CategoryEntity> results = findAll.stream().skip(skip).limit(numberLimit).collect(Collectors.toList());
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("results", results);
        data.put("total", total);
        return data;
    }

    public Boolean deleteCategory(UUID id) {
        cateRepo.deleteById(id);
        return true;
    }

    public Optional<CategoryEntity> findById(UUID id) {
        Optional<CategoryEntity> result = cateRepo.findById(id);
        return result;
    }
}
