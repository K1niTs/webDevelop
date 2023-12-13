package com.example.webdevelop.webDevelop.Services.ServicesIMPL;

import com.example.webdevelop.webDevelop.DTO.BrandDTO;
import com.example.webdevelop.webDevelop.Models.Brand;
import com.example.webdevelop.webDevelop.Repositories.BrandRepository;
import com.example.webdevelop.webDevelop.Services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class BrandServiceIMPL implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandServiceIMPL(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BrandDTO createBrand(BrandDTO brandDTO) {
        Brand brand = modelMapper.map(brandDTO, Brand.class);
        Brand savedBrand = brandRepository.save(brand);
        return modelMapper.map(savedBrand, BrandDTO.class);
    }

    @Override
    public BrandDTO getBrandById(UUID id) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isPresent()) {
            return modelMapper.map(brandOptional.get(), BrandDTO.class);
        }
        return null;
    }

    @Override
    public List<BrandDTO> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        return brands.stream()
                .map(brand -> modelMapper.map(brand, BrandDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BrandDTO updateBrand(UUID id, BrandDTO brandDTO) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isPresent()) {
            Brand brand = brandOptional.get();
            brand.setName(brandDTO.getName());
            Brand updateBrands = brandRepository.save(brand);
            return modelMapper.map(updateBrands, BrandDTO.class);
        }
        return null;
    }
    @Override
    public void deleteBrandById(UUID id) {
        brandRepository.deleteById(id);
    }

    @Override
    public List<Object[]> getBrandModelCountWhereStartYearGreaterThan2015() {
        return brandRepository.getBrandModelCountWhereStartYearGreaterThan2015();


    }
}
