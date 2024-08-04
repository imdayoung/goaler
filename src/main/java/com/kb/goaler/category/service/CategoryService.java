package com.kb.goaler.category.service;

import com.kb.goaler.category.dto.CategoryInfoResponse;
import com.kb.goaler.category.entity.CategoryEntity;
import com.kb.goaler.category.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryInfoResponse> getCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryInfoResponse> categoryInfoResponses = new ArrayList<>();
        for (CategoryEntity categoryEntity : categoryEntities) {
            CategoryInfoResponse response = CategoryInfoResponse.builder()
                    .idx(categoryEntity.getIdx())
                    .name(categoryEntity.getName())
                    .build();
            categoryInfoResponses.add(response);
        }
        return categoryInfoResponses;
    }
}
