package com.example.webdevelop.webDevelop.Services;

import com.example.webdevelop.webDevelop.DTO.BrandDTO;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    BrandDTO createBrand(BrandDTO brandDTO);

    BrandDTO getBrandById(UUID id);

    List<BrandDTO> getAllBrands();

    BrandDTO updateBrand(UUID id, BrandDTO brandDTO);

    void deleteBrand(UUID id);
    List<Object[]> getBrandModelCountWhereStartYearGreaterThan2015();
}
