package com.alevel.facade.impl;

import com.alevel.facade.CartFacade;
import com.alevel.persistence.datatable.DataTableRequest;
import com.alevel.persistence.datatable.DataTableResponse;
import com.alevel.persistence.entity.BaseEntity;
import com.alevel.persistence.entity.cart.Cart;
import com.alevel.service.CartService;
import com.alevel.util.WebUtil;
import com.alevel.web.dto.request.CartRequestDto;
import com.alevel.web.dto.response.CartResponseDto;
import com.alevel.web.dto.response.PageData;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartFacadeImpl implements CartFacade {

    private final CartService cartService;

    public CartFacadeImpl(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public int cartCountByUserId(Long userId, WebRequest webRequest) {
        int count;
        List<CartResponseDto> list = findAll(webRequest).getItems();
        count = (int) list.stream().filter(dto -> Objects.equals(dto.getUserId(), userId)).count();
        return count;
    }

    @Override
    public List<CartResponseDto> cartByUserId(Long userId, WebRequest request) {
        List<CartResponseDto> list = findAll(request).getItems();

        return findAll(request)
                .getItems().stream()
                .filter(dto -> Objects.equals(dto.getUserId(), userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> findCartIdByUserIdAndProductId(Long userId, Long productId) {
        List<Cart> carts = cartService.findCartIdByUserIdAndProductId(userId, productId);
        List<Long> cartIds = carts.stream().map(BaseEntity::getId).collect(Collectors.toList());
        return cartIds;
    }

    @Override
    public void create(CartRequestDto cartRequestDto) {
        Cart cart = new Cart();
        cart.setItemId(cartRequestDto.getItemId());
        cart.setUserId(cartRequestDto.getUserId());
        cart.setQuantity(cartRequestDto.getQuantity());
        cartService.create(cart);
    }

    @Override
    public void update(CartRequestDto cartRequestDto, Long id) {
        Optional<Cart> optionalCart = cartService.findById(id);
        if(optionalCart.isPresent()){
            Cart cart = optionalCart.get();
            cart.setItemId(cartRequestDto.getItemId());
            cart.setUserId(cartRequestDto.getUserId());
            cart.setQuantity(cartRequestDto.getQuantity());
            cartService.update(cart);
        }
    }

    @Override
    public void delete(Long id) {
        cartService.delete(id);
    }

    @Override
    public CartResponseDto findById(Long id) {
        Cart cart = cartService.findById(id).get();
        return new CartResponseDto(cart);
    }

    @Override
    public PageData<CartResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Cart> cartDataTableResponse = cartService.findAll(dataTableRequest);
        List<CartResponseDto> cartResponseDtos = cartDataTableResponse.getItems().stream()
                .map(CartResponseDto::new).collect(Collectors.toList());

        PageData<CartResponseDto> pageData = (PageData<CartResponseDto>) WebUtil.initPageData(cartDataTableResponse);
        pageData.setItems(cartResponseDtos);
        return pageData;
    }
}
