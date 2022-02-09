package com.alevel.web.dto.response;

import com.alevel.persistence.entity.category.Category;
import com.alevel.persistence.entity.item.Item;
import com.alevel.persistence.entity.manufacturer.Manufacturer;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

public class ItemResponseDto extends ResponseDto{

    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Integer quantity;
    private Set<Category> categories = Collections.emptySet();
    private Manufacturer manufacturer;

    public ItemResponseDto() { }

    public ItemResponseDto(Item item) {
        setId(item.getId());
        setCreated(item.getCreated());
        setUpdated(item.getUpdated());
        setVisible(item.getVisible());
        this.name = item.getName();
        this.description = item.getDescription();
        this.imageUrl = item.getImageUrl();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();

        if(CollectionUtils.isNotEmpty(item.getCategories())) {
            this.categories = item.getCategories();
        }

        if(item.getManufacturer() != null) {
            this.manufacturer = item.getManufacturer();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
