package com.example.webdevelop.webDevelop.Controllers;

import com.example.webdevelop.webDevelop.DTO.BrandDTO;
import com.example.webdevelop.webDevelop.DTO.OfferDTO;
import com.example.webdevelop.webDevelop.Services.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/add")
    public String createBrand(){
        return "brands-add";
    }
    @ModelAttribute("brandModel")
    public BrandDTO initBrand(){
        return new BrandDTO();
    }
    @PostMapping("/add")
    public String createBrand(@ModelAttribute("brandDTO") BrandDTO brandDTO) {
        brandService.createBrand(brandDTO);
        return "redirect:/brands/add";
    }
    @GetMapping("/{id}")
    public BrandDTO getBrandById(@PathVariable UUID id) {
        return brandService.getBrandById(id);
    }

    @GetMapping("/all")
    public String getAllBrands(Model model){
        List<BrandDTO> brandDTOS = brandService.getAllBrands();
        model.addAttribute("brands", brandDTOS);
        return "brands-all";
    }

    @PutMapping("/{id}")
    public BrandDTO updateBrand(@PathVariable UUID id, @Valid @RequestBody BrandDTO brandDTO) {
        return brandService.updateBrand(id, brandDTO);
    }


    @GetMapping("/brand-model-wherestart-2015")
    public List<Object[]> getBrandModelCountWhereStartYearGreaterThan2015() {
        return brandService.getBrandModelCountWhereStartYearGreaterThan2015();
    }
    @PostMapping("/delete/brands/{id}")
    public String deleteBrand(@PathVariable UUID id) {
        brandService.deleteBrandById(id);
        return "redirect:/brands/all";
    }
}
