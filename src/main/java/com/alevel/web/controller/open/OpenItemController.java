package com.alevel.web.controller.open;

import com.alevel.config.security.SecurityService;
import com.alevel.facade.ItemFacade;
import com.alevel.facade.PLPFacade;
import com.alevel.facade.UserFacade;
import com.alevel.util.SecurityUtil;
import com.alevel.web.dto.response.ItemResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.constraints.NotBlank;

@Controller
@RequestMapping("/items")
public class OpenItemController {
    private final PLPFacade plpFacade;
    private final ItemFacade itemFacade;
    private final UserFacade userFacade;
    private final SecurityService securityService;



    public OpenItemController(PLPFacade plpFacade, ItemFacade itemFacade, UserFacade userFacade, SecurityService securityService) {
        this.plpFacade = plpFacade;
        this.itemFacade = itemFacade;
        this.userFacade = userFacade;
        this.securityService = securityService;
    }
//    private final SearchItemFacade searchItemFacade;

    @GetMapping
    private String allItems(Model model, WebRequest webRequest) {
        boolean isAuthenticated = securityService.isAuthenticated();
        Long userId;
        if (isAuthenticated) {
            userId = userFacade.findByEmail(SecurityUtil.getUsername());
            model.addAttribute("userId", userId);
        }
        model.addAttribute("itemList", plpFacade.search(webRequest));
        return "pages/open/plp";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @NotBlank(message = "id can not be empty") String id, Model model) {
        try {
            Long itemId = Long.parseLong(id);
            ItemResponseDto dto = itemFacade.findById(itemId);
            boolean isAuthenticated = securityService.isAuthenticated();
            Long userId;
            if (isAuthenticated) {
                userId = userFacade.findByEmail(SecurityUtil.getUsername());
                model.addAttribute("userId", userId);
            }
            model.addAttribute("item", dto);
            return "pages/open/pdp";
        } catch (NumberFormatException e) {
            throw new NumberFormatException("incorrect value id");
        }
    }

    @PostMapping("/search")
    private String allBooksSearch(
            RedirectAttributes redirectAttributes, @RequestParam String itemSearch) {
        redirectAttributes.addAttribute("itemSearch", itemSearch);
        return "redirect:/items";
    }

//    @GetMapping("/suggestions")
//    @ResponseBody
//    public List<String> fetchSuggestions(@RequestParam(value = "q", required = false) String query) {
//        System.out.println("OpenBookController.fetchSuggestions: " + query);
//        return searchBookFacade.fetchSuggestions(query);
//    }
}
