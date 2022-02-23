package com.binary.mindset.cardealer.controller;

import com.binary.mindset.cardealer.BrandsApiDelegate;
import com.binary.mindset.cardealer.mapper.BrandMapper;
import com.binary.mindset.cardealer.model.BrandDto;
import com.binary.mindset.cardealer.model.BrandRequest;
import com.binary.mindset.cardealer.model.BrandResponse;
import com.binary.mindset.cardealer.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
public class BrandController implements BrandsApiDelegate {

    private final BrandService brandService;
    private final BrandMapper brandMapper;

    @Autowired
    public BrandController(final BrandService brandService,
                           final BrandMapper brandMapper) {
        this.brandService = brandService;
        this.brandMapper = brandMapper;
    }

    @Override
    public ResponseEntity<BrandResponse> createBrand(BrandRequest brandRequest) {
        BrandDto brandDto = brandService.createBrand(brandMapper.toBrandDto(brandRequest));
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(brandDto.getId())
                        .toUri())
                .body(brandMapper.toBrandResponse(brandDto));
    }

    @Override
    public ResponseEntity<List<BrandResponse>> getBrands() {
        List<BrandDto> brandDtos = brandService.getBrands();
        return ResponseEntity.ok()
                .body(brandMapper.toBrandResponseAsList(brandDtos));
    }

    @Override
    public ResponseEntity<BrandResponse> getBrand(Integer brandId) {
        return ResponseEntity.ok()
                .body(brandMapper.toBrandResponse(brandService.getBrand(brandId)));
    }

    @Override
    public ResponseEntity<Void> deleteBrand(Integer brandId) {
        brandService.deleteBrand(brandId);
        return ResponseEntity.noContent().build();
    }
}