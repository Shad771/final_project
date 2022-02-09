package com.alevel.facade.impl;

import com.alevel.facade.OrderFacade;
import com.alevel.persistence.datatable.DataTableRequest;
import com.alevel.persistence.datatable.DataTableResponse;
import com.alevel.persistence.entity.order.Order;
import com.alevel.service.OrderService;
import com.alevel.util.WebUtil;
import com.alevel.web.dto.request.OrderRequestDto;
import com.alevel.web.dto.response.OrderResponseDto;
import com.alevel.web.dto.response.PageData;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;

    public OrderFacadeImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void create(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setCartId(orderRequestDto.getCartId());
        order.setCustomerName(orderRequestDto.getCustomerName());
        order.setCustomerEmail(orderRequestDto.getCustomerEmail());
        order.setCustomerPhone(orderRequestDto.getCustomerPhone());
        order.setCustomerAddress(orderRequestDto.getCustomerAddress());
        orderService.create(order);
    }

    @Override
    public void update(OrderRequestDto orderRequestDto, Long id) {
        Optional<Order> optionalOrder = orderService.findById(id);
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.setCartId(orderRequestDto.getCartId());
            order.setCustomerName(orderRequestDto.getCustomerName());
            order.setCustomerEmail(orderRequestDto.getCustomerEmail());
            order.setCustomerAddress(orderRequestDto.getCustomerAddress());
            order.setCustomerPhone(orderRequestDto.getCustomerPhone());
            orderService.update(order);
        }
    }

    @Override
    public void delete(Long id) {
        orderService.delete(id);
    }

    @Override
    public OrderResponseDto findById(Long id) {
        Order order = orderService.findById(id).get();
        return new OrderResponseDto(order);
    }

    @Override
    public PageData<OrderResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Order> orderDataTableResponse = orderService.findAll(dataTableRequest);
        List<OrderResponseDto> manufacturers = orderDataTableResponse.getItems().stream()
                .map(OrderResponseDto::new).collect(Collectors.toList());

        PageData<OrderResponseDto> pageData = (PageData<OrderResponseDto>) WebUtil.initPageData(orderDataTableResponse);
        pageData.setItems(manufacturers);
        return pageData;
    }
}
