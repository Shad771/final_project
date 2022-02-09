package com.alevel.web.controller.order;

import com.alevel.config.security.SecurityService;
import com.alevel.facade.CartFacade;
import com.alevel.facade.OrderFacade;
import com.alevel.web.dto.request.OrderRequestDto;
import com.alevel.web.dto.response.CartResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderFacade orderFacade;
    private final CartFacade cartFacade;
    private final SecurityService securityService;


    public OrderController(OrderFacade orderFacade, CartFacade cartFacade, SecurityService securityService) {
        this.orderFacade = orderFacade;
        this.cartFacade = cartFacade;
        this.securityService = securityService;
    }

    @PostMapping("/create/{userId}")
    public String createOrder(@PathVariable Long userId, WebRequest webRequest, @ModelAttribute("order") OrderRequestDto dto) {
        List<CartResponseDto> cart = cartFacade.cartByUserId(userId, webRequest);

        for (CartResponseDto cartResponseDto : cart) {
            dto.setCartId(cartResponseDto.getId());
            orderFacade.create(dto);
        }
        return "redirect:/items";
    }

    @GetMapping("/new/{userId}")
    public String redirectToNewOrderPage(@PathVariable Long userId, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("order", new OrderRequestDto());
        return "pages/order/order_new";
    }


}
