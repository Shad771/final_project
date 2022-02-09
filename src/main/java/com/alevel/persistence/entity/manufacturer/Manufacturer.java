package com.alevel.persistence.entity.manufacturer;

import com.alevel.persistence.entity.BaseEntity;
import com.neovisionaries.i18n.CountryCode;

import javax.persistence.*;

@Entity
@Table(name = "manufacturers")
public class Manufacturer extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

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

    @Enumerated(EnumType.STRING)
    private CountryCode country;


}
