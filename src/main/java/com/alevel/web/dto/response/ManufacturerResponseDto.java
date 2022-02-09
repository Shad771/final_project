package com.alevel.web.dto.response;

import com.alevel.persistence.entity.manufacturer.Manufacturer;
import com.neovisionaries.i18n.CountryCode;

public class ManufacturerResponseDto extends ResponseDto {
    private String name;
    private String address;
    private CountryCode country;

    public ManufacturerResponseDto() {
    }

    public ManufacturerResponseDto(Manufacturer manufacturer) {
        setId(manufacturer.getId());
        setCreated(manufacturer.getCreated());
        setUpdated(manufacturer.getUpdated());
        setVisible(manufacturer.getVisible());
        this.name = manufacturer.getName();
        this.address = manufacturer.getAddress();
        this.country = manufacturer.getCountry();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CountryCode getCountry() {
        return country;
    }

    public void setCountry(CountryCode country) {
        this.country = country;
    }
}
