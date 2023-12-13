package com.example.webdevelop.webDevelop.Controllers;

import com.example.webdevelop.webDevelop.Controllers.views.CarViewModel;
import com.example.webdevelop.webDevelop.DTO.BrandDTO;
import com.example.webdevelop.webDevelop.DTO.ModelDTO;
import com.example.webdevelop.webDevelop.DTO.OfferDTO;
import com.example.webdevelop.webDevelop.Models.Brand;
import org.springframework.util.StopWatch;
import com.example.webdevelop.webDevelop.Services.BrandService;
import com.example.webdevelop.webDevelop.Services.ModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.webdevelop.webDevelop.Services.OfferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;
    private final BrandService brandService;
    private final ModelService modelService;

    @Autowired
    public OfferController(OfferService offerService, BrandService brandService, ModelService modelService) {
        this.offerService = offerService;
        this.brandService = brandService;
        this.modelService = modelService;
    }

    @PostMapping("/create")
    public String createOffer(@Valid @ModelAttribute("offerDTO") OfferDTO offerDTO, Model model) {
        OfferDTO createdOffer = offerService.createOffer(offerDTO);
        model.addAttribute("offerDTO", createdOffer);
        return "redirect:/offers/details/" + createdOffer.getId();
    }

    @GetMapping("/details/{offer-id}")
    public String offerDetails(@PathVariable("offer-id") UUID offerId, Model model) {
        OfferDTO offerDTO = offerService.getOfferById(offerId);
        model.addAttribute("offerDetails", offerDTO);
        return "offers-details";
    }

    @GetMapping("/all")
    public String getAllOffers(Model model) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<OfferDTO> offerDTOs = offerService.getAllOffers();
        stopWatch.stop();
        System.out.println("getAllOffers execution time: " + stopWatch.getTotalTimeMillis() + " ms");
        model.addAttribute("offers", offerDTOs);
        return "offers-all";
    }

    @PutMapping("/{id}")
    public OfferDTO updateOffer(@PathVariable UUID id, @Valid @RequestBody OfferDTO offerDTO) {
        return offerService.updateOffer(id, offerDTO);
    }

    @GetMapping("/offers-with-model-and-brand")
    public List<Object[]> findOffersWithModelAndBrand() {
        return offerService.findOffersWithModelAndBrand();
    }

    @PostMapping("/delete/offers/{id}")
    public String deleteOffer(@PathVariable UUID id) {
        offerService.deleteOffer(id);
        return "redirect:/offers/all";
    }

    @GetMapping("/add")
    public String createOffer(Model model) {
        List<ModelDTO> models = modelService.getAllModels();
        List<BrandDTO> brands = brandService.getAllBrands();

        model.addAttribute("models", models);
        model.addAttribute("brands", brands);

        return "offers-add";
    }

    @ModelAttribute("offerModel")
    public OfferDTO initOffer() {
        return new OfferDTO();
    }

    @PostMapping("/add")
    public String createOffer(@ModelAttribute("offerDTO") OfferDTO offerDTO) {
        offerService.createOffer(offerDTO);
        return "redirect:/offers/add";

    }
    @GetMapping("/view")
    public String carPage(Model model){
        List<CarViewModel> carViewModel = offerService.getOfferCar();
        model.addAttribute("carView", carViewModel);
        return "view";
    }
}


