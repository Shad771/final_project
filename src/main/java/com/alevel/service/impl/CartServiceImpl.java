package com.alevel.service.impl;

import com.alevel.persistence.crud.CrudRepositoryHelper;
import com.alevel.persistence.datatable.DataTableRequest;
import com.alevel.persistence.datatable.DataTableResponse;
import com.alevel.persistence.entity.cart.Cart;
import com.alevel.persistence.repository.item.CartRepository;
import com.alevel.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CrudRepositoryHelper<Cart, CartRepository> cartRepositoryHelper;
    private final CartRepository cartRepository;

    public CartServiceImpl(CrudRepositoryHelper<Cart, CartRepository> cartRepositoryHelper, CartRepository cartRepository) {
        this.cartRepositoryHelper = cartRepositoryHelper;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(Cart entity) {
        cartRepositoryHelper.create(cartRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(Cart entity) {
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        cartRepositoryHelper.delete(cartRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cart> findById(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Cart> findAll(DataTableRequest dataTableRequest) {

        return cartRepositoryHelper.findAll(cartRepository, dataTableRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cart> findCartIdByUserIdAndProductId(Long userId, Long productId) {
        return cartRepository.findCartIdByUserIdAndItemId(userId, productId);
    }

}
