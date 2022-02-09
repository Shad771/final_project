package com.alevel.web.controller.admin;

import com.alevel.facade.OrderFacade;
import com.alevel.web.controller.AbstractController;
import com.alevel.web.dto.response.OrderResponseDto;
import com.alevel.web.dto.response.PageData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Validated
@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController extends AbstractController{

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("order id", "orderId", "order_id"),
            new HeaderName("created", "created", "created"),
            new HeaderName("email", "email", "email"),
            new HeaderName("address", "address", "address"),
            new HeaderName("details", null, null),
            new HeaderName("delete", null, null)
    };

    private final OrderFacade orderFacade;

    public AdminOrderController(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<OrderResponseDto> response = orderFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/orders/all");
        model.addAttribute("createNew", "/admin/orders/new");
        model.addAttribute("cardHeader", "All orders");
        return "pages/admin/order/order_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "items");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable @Min(value = 1, message = "id can not be zero") Long id) {
        orderFacade.delete(id);
        return "redirect:/admin/orders";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @NotBlank(message = "id can not be empty") String id, Model model) {
        try {
            Long orderId = Long.parseLong(id);
            OrderResponseDto dto = orderFacade.findById(orderId);
            model.addAttribute("order", dto);
            return "pages/admin/order/order_details";
        } catch (NumberFormatException e) {
            throw new NumberFormatException("incorrect value id");
        }

    }
}
