package com.alevel.service.impl;

import com.alevel.persistence.crud.CrudRepositoryHelper;
import com.alevel.persistence.datatable.DataTableRequest;
import com.alevel.persistence.datatable.DataTableResponse;
import com.alevel.persistence.entity.manufacturer.Manufacturer;
import com.alevel.persistence.repository.item.ManufacturerRepository;
import com.alevel.service.ManufacturerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
    private final CrudRepositoryHelper<Manufacturer, ManufacturerRepository> crudRepositoryHelper;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, CrudRepositoryHelper<Manufacturer, ManufacturerRepository> crudRepositoryHelper) {
        this.manufacturerRepository = manufacturerRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }


    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Manufacturer entity) {
        crudRepositoryHelper.create(manufacturerRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Manufacturer entity) {
        crudRepositoryHelper.update(manufacturerRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        crudRepositoryHelper.delete(manufacturerRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Manufacturer> findById(Long id) {
        return crudRepositoryHelper.findById(manufacturerRepository, id);
    }

    @Override
    public DataTableResponse<Manufacturer> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(manufacturerRepository, request);
    }
}
