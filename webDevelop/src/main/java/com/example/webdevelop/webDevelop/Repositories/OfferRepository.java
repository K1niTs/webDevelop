package com.example.webdevelop.webDevelop.Repositories;

import com.example.webdevelop.webDevelop.Models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository <Offer, UUID> {

    @Query("SELECT o.description, m.name AS model_name, b.name AS brand_name FROM Offer o " +
            "JOIN Model m ON o.model.id = m.id " +
            "JOIN Brand b ON m.brand.id = b.id " +
            "WHERE o.price >= 4000000")
    List<Object[]> findOffersWithModelAndBrand();

}

