package com.example.webdevelop.webDevelop.Services.ServicesIMPL;
import com.example.webdevelop.webDevelop.DTO.OfferDTO;
import com.example.webdevelop.webDevelop.Models.Offer;
import com.example.webdevelop.webDevelop.Repositories.OfferRepository;
import com.example.webdevelop.webDevelop.Services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OfferServiceIMPL implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceIMPL(OfferRepository offerRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OfferDTO createOffer(OfferDTO offerDTO) {
        Offer offer = modelMapper.map(offerDTO, Offer.class);
        Offer savedOffer = offerRepository.save(offer);
        return modelMapper.map(savedOffer, OfferDTO.class);
    }

    @Override
    public OfferDTO getOfferById(UUID id) {
        Optional<Offer> offerOptional = offerRepository.findById(id);
        if (offerOptional.isPresent()) {
            return modelMapper.map(offerOptional.get(), OfferDTO.class);
        }
        return null;
    }

    @Override
    public List<OfferDTO> getAllOffers() {
        List<Offer> offers = offerRepository.findAll();
        return offers.stream()
                .map(offer -> modelMapper.map(offer, OfferDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OfferDTO updateOffer(UUID id, OfferDTO offerDTO) {
        Optional<Offer> offerOptional = offerRepository.findById(id);
        if (offerOptional.isPresent()) {
            Offer offer = offerOptional.get();
            offer.setImageUrl(offerDTO.getImageUrl());
            Offer updateModels = offerRepository.save(offer);
            return modelMapper.map(updateModels, OfferDTO.class);
        } else {

            return null;
        }
    }

    @Override
    public void deleteOffer(UUID id) {
        offerRepository.deleteById(id);
    }

    public List<Object[]> findOffersWithModelAndBrand() {
        return offerRepository.findOffersWithModelAndBrand();

    }


    }

