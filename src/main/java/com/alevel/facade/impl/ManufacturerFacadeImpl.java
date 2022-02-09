package com.alevel.facade.impl;

import com.alevel.facade.ManufacturerFacade;
import com.alevel.persistence.datatable.DataTableRequest;
import com.alevel.persistence.datatable.DataTableResponse;
import com.alevel.persistence.entity.manufacturer.Manufacturer;
import com.alevel.service.ManufacturerService;
import com.alevel.util.WebUtil;
import com.alevel.web.dto.request.ManufacturerRequestDto;
import com.alevel.web.dto.response.ManufacturerResponseDto;
import com.alevel.web.dto.response.PageData;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManufacturerFacadeImpl implements ManufacturerFacade {

    private final ManufacturerService manufacturerService;

    public ManufacturerFacadeImpl(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @Override
    public void create(ManufacturerRequestDto manufacturerRequestDto) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(manufacturerRequestDto.getName());
        manufacturer.setAddress(manufacturerRequestDto.getAddress());
        manufacturer.setCountry(manufacturerRequestDto.getCountry());
        manufacturerService.create(manufacturer);
    }

    @Override
    public void update(ManufacturerRequestDto manufacturerRequestDto, Long id) {
        Optional<Manufacturer> optionalManufacturer = manufacturerService.findById(id);
        if (optionalManufacturer.isPresent()) {
            Manufacturer manufacturer = optionalManufacturer.get();
            manufacturer.setName(manufacturerRequestDto.getName());
            manufacturer.setAddress(manufacturerRequestDto.getAddress());
            manufacturer.setCountry(manufacturerRequestDto.getCountry());
            manufacturerService.update(manufacturer);
        }
    }

    @Override
    public void delete(Long id) {
        manufacturerService.delete(id);
    }

    @Override
    public ManufacturerResponseDto findById(Long id) {
        Manufacturer manufacturer = manufacturerService.findById(id).get();
        return new ManufacturerResponseDto(manufacturer);
    }

    @Override
    public PageData<ManufacturerResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Manufacturer> manufacturerDataTableResponse = manufacturerService.findAll(dataTableRequest);
        List<ManufacturerResponseDto> manufacturers = manufacturerDataTableResponse.getItems().stream()
                .map(ManufacturerResponseDto::new).collect(Collectors.toList());

        PageData<ManufacturerResponseDto> pageData = (PageData<ManufacturerResponseDto>) WebUtil.initPageData(manufacturerDataTableResponse);
        pageData.setItems(manufacturers);
        return pageData;
    }
}
