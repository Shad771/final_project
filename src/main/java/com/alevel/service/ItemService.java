package com.alevel.service;

import com.alevel.persistence.datatable.DataTableResponse;
import com.alevel.persistence.entity.item.Item;
import com.alevel.persistence.entity.manufacturer.Manufacturer;


public interface ItemService extends BaseCrudService<Item> {

    DataTableResponse<Item> findByManufacturer(Manufacturer manufacturer);
    DataTableResponse<Item> findByCategories(Long categoriesId);


}
