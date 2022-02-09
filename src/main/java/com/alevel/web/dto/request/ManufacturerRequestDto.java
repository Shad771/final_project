package com.alevel.web.dto.request;

import com.neovisionaries.i18n.CountryCode;

public class ManufacturerRequestDto extends RequestDto{
    private String name;
    private String address;
    private CountryCode country;

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

    @Override
    public String toString() {
        return "ManufacturerRequestDto{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
