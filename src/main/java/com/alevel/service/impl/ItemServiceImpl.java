package com.alevel.service.impl;

import com.alevel.persistence.crud.CrudRepositoryHelper;
import com.alevel.persistence.datatable.DataTableRequest;
import com.alevel.persistence.datatable.DataTableResponse;
import com.alevel.persistence.entity.item.Item;
import com.alevel.persistence.entity.manufacturer.Manufacturer;
import com.alevel.persistence.repository.item.ItemRepository;
import com.alevel.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CrudRepositoryHelper<Item, ItemRepository> crudRepositoryHelper;

    public ItemServiceImpl(ItemRepository itemRepository, CrudRepositoryHelper<Item, ItemRepository> crudRepositoryHelper) {
        this.itemRepository = itemRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Item entity) {
        crudRepositoryHelper.create(itemRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Item entity) {
        crudRepositoryHelper.update(itemRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        crudRepositoryHelper.delete(itemRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Item> findById(Long id) {
        return crudRepositoryHelper.findById(itemRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Item> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(itemRepository, request);
    }

    @Override
    public DataTableResponse<Item> findByManufacturer(Manufacturer manufacturer) {
        return (DataTableResponse<Item>) itemRepository.findByManufacturer(Optional.ofNullable(manufacturer));
    }

    @Override
    public DataTableResponse<Item> findByCategories(Long categoriesId) {
        return (DataTableResponse<Item>) itemRepository.findByCategories(categoriesId);
    }
}
