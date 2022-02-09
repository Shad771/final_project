package com.alevel.web.dto.request;

public class ItemRequestDto extends RequestDto{
    private String name;
    private String description;
    private String itemImage;

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

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    @Override
    public String toString() {
        return "ItemRequestDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", itemImage='" + itemImage + '\'' +
                '}';
    }
}
