package com.example.webdevelop.webDevelop.Repositories;

import com.example.webdevelop.webDevelop.Models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository <Offer, Integer> {

}