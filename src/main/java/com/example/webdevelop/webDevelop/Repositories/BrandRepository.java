package com.example.webdevelop.webDevelop.Repositories;

import com.example.webdevelop.webDevelop.Models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BrandRepository extends JpaRepository <Brand, UUID> {
        @Query
                ("SELECT B.name AS brand_name, COUNT(M.id) AS model_count " +
                "FROM Brand B " +
                "LEFT JOIN B.models M " +
                "WHERE M.startYear > 2015 " +
                "GROUP BY B.name")
        List<Object[]> getBrandModelCountWhereStartYearGreaterThan2015();
    }
