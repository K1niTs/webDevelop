package com.example.webdevelop.webDevelop.Services.ServicesIMPL;
import com.example.webdevelop.webDevelop.Controllers.views.CarViewModel;
import com.example.webdevelop.webDevelop.DTO.BrandDTO;
import com.example.webdevelop.webDevelop.DTO.ModelDTO;
import com.example.webdevelop.webDevelop.DTO.OfferDTO;
import com.example.webdevelop.webDevelop.Models.Offer;
import com.example.webdevelop.webDevelop.Repositories.OfferRepository;
import com.example.webdevelop.webDevelop.Services.OfferService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class OfferServiceIMPL implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceIMPL(OfferRepository offerRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    @CacheEvict(value = "offerCache", allEntries = true)
    public OfferDTO createOffer(OfferDTO offerDTO) {
        Offer offer = modelMapper.map(offerDTO, Offer.class);
        Offer savedOffer = offerRepository.save(offer);
        return modelMapper.map(savedOffer, OfferDTO.class);
    }

    @Override
    @Cacheable(value = "offerCache", key = "#id")
    public OfferDTO getOfferById(UUID id) {
        Optional<Offer> offerOptional = offerRepository.findById(id);
        if (offerOptional.isPresent()) {
            return modelMapper.map(offerOptional.get(), OfferDTO.class);
        }
        return null;
    }

    @Override
    @Cacheable(value = "offerCache")
    public List<OfferDTO> getAllOffers() {
        List<Offer> offers = offerRepository.findAll();
        return offers.stream()
                .map(offer -> modelMapper.map(offer, OfferDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "offerCache", key = "#id")
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
    @CacheEvict(value = "offerCache", key = "#id")
    public void deleteOffer(UUID id) {
        offerRepository.deleteById(id);
    }

    public List<Object[]> findOffersWithModelAndBrand() {
        return offerRepository.findOffersWithModelAndBrand();


    }

    @Override
    public List<CarViewModel> getOfferCar() {
        List<OfferDTO> offerDTOS = getAllOffers();

        return offerDTOS.stream()
                .map(offerDTO -> {
                    BrandDTO brandDTO = offerDTO.getModel().getBrand();
                    ModelDTO modelDTO = offerDTO.getModel();
                    return new CarViewModel(
                            offerDTO.getId(),
                            modelDTO.getImageURL(),
                            brandDTO.getName(),
                            modelDTO.getName(),
                            offerDTO.getDescription(),
                            offerDTO.getMileage(),
                            offerDTO.getPrice()
                    );
                })
                .collect(Collectors.toList());

    }




}


