package com.alevel.facade;

import com.alevel.web.dto.request.OrderRequestDto;
import com.alevel.web.dto.response.OrderResponseDto;

public interface OrderFacade extends CrudFacade<OrderRequestDto, OrderResponseDto> {
}
