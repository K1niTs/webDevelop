package com.example.webdevelop.webDevelop.Repositories;

import com.example.webdevelop.webDevelop.Controllers.views.ModelViewModel;
import com.example.webdevelop.webDevelop.Models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ModelRepository extends JpaRepository <Model, UUID> {
        @Query("SELECT m.name AS model_name, b.name AS brand_name FROM Model m " +
                "JOIN Brand b ON m.brand.id = b.id " +
                "WHERE m.imageURL LIKE 'https://example.com/renault%'")
        List<Object[]> findModelAndBrandByImageURL();

        List<Model> findAll();
}