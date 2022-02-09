package com.alevel.service;

import com.alevel.persistence.datatable.DataTableRequest;
import com.alevel.persistence.datatable.DataTableResponse;
import com.alevel.persistence.entity.BaseEntity;

import java.util.Optional;

public interface BaseCrudService<E extends BaseEntity> {

    void create(E entity);
    void update(E entity);
    void delete(Long id);
    Optional<E> findById(Long id);
    DataTableResponse<E> findAll(DataTableRequest request);
}
