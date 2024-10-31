package com.kb.goaler.category.repository;

import com.kb.goaler.category.entity.CategoryEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    @NonNull
    List<CategoryEntity> findAll();
}
