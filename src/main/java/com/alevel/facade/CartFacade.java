package com.alevel.facade;

import com.alevel.web.dto.request.CartRequestDto;
import com.alevel.web.dto.response.CartResponseDto;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

public interface CartFacade extends CrudFacade<CartRequestDto, CartResponseDto> {

    int cartCountByUserId(Long userId, WebRequest webRequest);

    List<CartResponseDto> cartByUserId(Long userId, WebRequest request);

    List<Long> findCartIdByUserIdAndProductId(Long userId, Long productId);
}
