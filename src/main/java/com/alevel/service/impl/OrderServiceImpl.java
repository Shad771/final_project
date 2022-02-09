package com.alevel.service.impl;

import com.alevel.persistence.crud.CrudRepositoryHelper;
import com.alevel.persistence.datatable.DataTableRequest;
import com.alevel.persistence.datatable.DataTableResponse;
import com.alevel.persistence.entity.order.Order;
import com.alevel.persistence.repository.item.OrderRepository;
import com.alevel.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CrudRepositoryHelper<Order, OrderRepository> crudRepositoryHelper;

    public OrderServiceImpl(OrderRepository orderRepository, CrudRepositoryHelper<Order, OrderRepository> crudRepositoryHelper) {
        this.orderRepository = orderRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Order entity) {
        crudRepositoryHelper.create(orderRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Order entity) {
        crudRepositoryHelper.update(orderRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        crudRepositoryHelper.delete(orderRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> findById(Long id) {
        return crudRepositoryHelper.findById(orderRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Order> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(orderRepository, request);
    }
}
