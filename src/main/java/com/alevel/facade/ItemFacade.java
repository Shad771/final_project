package com.alevel.facade;

import com.alevel.web.dto.request.ItemRequestDto;
import com.alevel.web.dto.response.ItemResponseDto;
import com.alevel.web.dto.response.PageData;
import org.springframework.web.context.request.WebRequest;

public interface ItemFacade extends CrudFacade<ItemRequestDto, ItemResponseDto>{

    PageData<ItemResponseDto> findAllByManufacturerId(WebRequest request, Long manufacturerId);

}
