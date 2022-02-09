package com.alevel.web.controller.admin;

import com.alevel.facade.ManufacturerFacade;
import com.alevel.web.controller.AbstractController;
import com.alevel.web.dto.response.ManufacturerResponseDto;
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
@RequestMapping("/admin/manufacturers")
public class AdminManufacturerController extends AbstractController {

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("manufacturer name", "manName", "manufacturer_name"),
            new HeaderName("created", "created", "created"),
            new HeaderName("address", "address", "address"),
            new HeaderName("countryCode", "countryCode", "countryCode"),
            new HeaderName("details", null, null),
            new HeaderName("delete", null, null)
    };

    private final ManufacturerFacade manufacturerFacade;

    public AdminManufacturerController(ManufacturerFacade manufacturerFacade) {
        this.manufacturerFacade = manufacturerFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<ManufacturerResponseDto> response = manufacturerFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/manufacturer/all");
        model.addAttribute("createNew", "/admin/manufacturer/new");
        model.addAttribute("cardHeader", "All manufacturers");
        return "pages/admin/manufacturer/manufacturer_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "items");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable @Min(value = 1, message = "id can not be zero") Long id) {
        manufacturerFacade.delete(id);
        return "redirect:/admin/manufacturers";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @NotBlank(message = "id can not be empty") String id, Model model) {
        try {
            Long manufacturerId = Long.parseLong(id);
            ManufacturerResponseDto dto = manufacturerFacade.findById(manufacturerId);
            model.addAttribute("manufacturer", dto);
            return "pages/admin/manufacturer/manufacturer_details";
        } catch (NumberFormatException e) {
            throw new NumberFormatException("incorrect value id");
        }

    }
}
