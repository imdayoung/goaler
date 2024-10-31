package com.kb.goaler.category.controller;

import com.kb.goaler.category.dto.CategoryInfoResponse;
import com.kb.goaler.category.service.CategoryService;
import com.kb.goaler.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<CategoryInfoResponse>>> getAllCategories() {
        List<CategoryInfoResponse> responses = categoryService.getCategories();
        return ResponseEntity.ok().body(ApiResponse.success(responses));
    }
}
