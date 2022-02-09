package com.alevel.web.controller.cart;

import com.alevel.config.security.SecurityService;
import com.alevel.facade.CartFacade;
import com.alevel.facade.ItemFacade;
import com.alevel.facade.UserFacade;
import com.alevel.util.SecurityUtil;
import com.alevel.web.dto.request.CartRequestDto;
import com.alevel.web.dto.response.CartResponseDto;
import com.alevel.web.dto.response.ItemResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartFacade cartFacade;
    private final ItemFacade itemFacade;
    private final UserFacade userFacade;
    private final SecurityService securityService;

    public CartController(CartFacade cartFacade, ItemFacade itemFacade, UserFacade userFacade, SecurityService securityService) {
        this.cartFacade = cartFacade;
        this.itemFacade = itemFacade;
        this.userFacade = userFacade;
        this.securityService = securityService;
    }

    @GetMapping("/{userId}/{itemId}")
    public String productToCart(@PathVariable Long itemId, Model model, RedirectAttributes redirectAttributes) {
        CartRequestDto cartRequestDto = new CartRequestDto();
        cartRequestDto.setItemId(itemId);

        boolean isAuthenticated = securityService.isAuthenticated();
        Long userId = null;
        if (isAuthenticated) {
            userId = userFacade.findByEmail(SecurityUtil.getUsername());
        }
        cartRequestDto.setUserId(userId);
        cartFacade.create(cartRequestDto);

        redirectAttributes.addFlashAttribute("message", "Product added to cart");
        redirectAttributes.addFlashAttribute("visibility", true);
        return "redirect:/items";
    }

    @GetMapping("/{userId}")
    public String cart(@PathVariable Long userId, WebRequest webRequest, Model model) {
        List<CartResponseDto> cart = cartFacade.cartByUserId(userId, webRequest);
        List<ItemResponseDto> items = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        int count = 0;
        for (CartResponseDto dto: cart) {
            items.add(itemFacade.findById(dto.getItemId()));
            totalPrice = totalPrice.add(itemFacade.findById(dto.getItemId()).getPrice());
            count++;
        }
        model.addAttribute("userId", userId);
        model.addAttribute("itemList", items);
        model.addAttribute("totalPrice", totalPrice.setScale(2));
        model.addAttribute("count", count);
        model.addAttribute("userName", SecurityUtil.getUsername());
        return "pages/cart/cart";
    }

    @GetMapping("/remove/{userId}/{itemId}")
    public String removeFromCart(@PathVariable Long userId, @PathVariable Long itemId) {
        List<Long> cartIds = cartFacade.findCartIdByUserIdAndProductId(userId, itemId);
        if (cartIds.get(0) != null) {
            cartFacade.delete(cartIds.get(0));
        }

        return "redirect:/cart/" + userId;
    }
}
