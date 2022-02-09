package com.alevel.persistence.crud;

import com.alevel.persistence.datatable.DataTableRequest;
import com.alevel.persistence.datatable.DataTableResponse;
import com.alevel.persistence.entity.BaseEntity;
import com.alevel.persistence.repository.BaseRepository;

import java.util.Optional;

public interface CrudRepositoryHelper<E extends BaseEntity, R extends BaseRepository<E>> {

    void create(R repository, E entity);
    void update(R repository, E entity);
    void delete(R repository, Long id);
    Optional<E> findById(R repository, Long id);
    DataTableResponse<E> findAll(R repository, DataTableRequest request);
}
