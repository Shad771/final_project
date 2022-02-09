package com.alevel.persistence.repository.item;

import com.alevel.persistence.entity.cart.Cart;
import com.alevel.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends BaseRepository<Cart> {

    List<Cart> findCartIdByUserIdAndItemId(Long userId, Long productId);
}
