package com.example.webdevelop.webDevelop.Services;

import com.example.webdevelop.webDevelop.Controllers.views.CarViewModel;
import com.example.webdevelop.webDevelop.DTO.OfferDTO;
import com.example.webdevelop.webDevelop.Models.Offer;

import java.util.List;
import java.util.UUID;

public interface OfferService {
    OfferDTO createOffer(OfferDTO offerDTO);
    OfferDTO getOfferById(UUID id);
    List<OfferDTO> getAllOffers();
    OfferDTO updateOffer(UUID id, OfferDTO offerDTO);
    void deleteOffer(UUID id);
    List<Object[]> findOffersWithModelAndBrand();
    List<CarViewModel> getOfferCar();


    }

