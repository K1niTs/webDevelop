package com.example.webdevelop.webDevelop.Controllers;

import com.example.webdevelop.webDevelop.DTO.OfferDTO;
import com.example.webdevelop.webDevelop.Services.OfferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/create")
    public OfferDTO createOffer(@Valid @RequestBody OfferDTO offerDTO) {
        return offerService.createOffer(offerDTO);
    }

    @GetMapping("/{id}")
    public OfferDTO getOfferById(@PathVariable UUID id) {
        return offerService.getOfferById(id);
    }

    @GetMapping("/all")
    public List<OfferDTO> getAllOffers() {
        return offerService.getAllOffers();
    }

    @PutMapping("/{id}")
    public OfferDTO updateOffer(@PathVariable UUID id,@Valid @RequestBody OfferDTO offerDTO) {
        return offerService.updateOffer(id, offerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable UUID id) {
        offerService.deleteOffer(id);
    }

    @GetMapping("/offers-with-model-and-brand")
    public List<Object[]> findOffersWithModelAndBrand() {
        return offerService.findOffersWithModelAndBrand();
    }
}
