package com.example.webdevelop.webDevelop.Controllers;

import com.example.webdevelop.webDevelop.DTO.BrandDTO;
import com.example.webdevelop.webDevelop.Services.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/create")
    public BrandDTO createBrand(@Valid @RequestBody BrandDTO brandDTO) {
        return brandService.createBrand(brandDTO);
    }

    @GetMapping("/{id}")
    public BrandDTO getBrandById(@PathVariable UUID id) {
        return brandService.getBrandById(id);
    }

    @GetMapping("/all")
    public List<BrandDTO> getAllBrands() {
        return brandService.getAllBrands();
    }

    @PutMapping("/{id}")
    public BrandDTO updateBrand(@PathVariable UUID id, @Valid @RequestBody BrandDTO brandDTO) {
        return brandService.updateBrand(id, brandDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable UUID id) {
        brandService.deleteBrand(id);
    }

    @GetMapping("/brand-model-wherestart-2015")
    public List<Object[]> getBrandModelCountWhereStartYearGreaterThan2015() {
        return brandService.getBrandModelCountWhereStartYearGreaterThan2015();
    }
}
