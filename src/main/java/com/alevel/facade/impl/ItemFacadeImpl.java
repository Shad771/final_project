package com.alevel.facade.impl;

import com.alevel.facade.ItemFacade;
import com.alevel.persistence.datatable.DataTableRequest;
import com.alevel.persistence.datatable.DataTableResponse;
import com.alevel.persistence.entity.item.Item;
import com.alevel.service.ItemService;
import com.alevel.util.WebUtil;
import com.alevel.web.dto.request.ItemRequestDto;
import com.alevel.web.dto.response.ItemResponseDto;
import com.alevel.web.dto.response.PageData;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemFacadeImpl implements ItemFacade {

    private final ItemService itemService;

    public ItemFacadeImpl(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void create(ItemRequestDto itemRequestDto) {
        Item item = new Item();
        item.setName(itemRequestDto.getName());
        item.setDescription(itemRequestDto.getDescription());
        item.setImageUrl(itemRequestDto.getItemImage());
        itemService.create(item);
    }

    @Override
    public void update(ItemRequestDto itemRequestDto, Long id) {
        Optional<Item> optionalItem = itemService.findById(id);
        if(optionalItem.isPresent()){
            Item item = optionalItem.get();
            item.setName(itemRequestDto.getName());
            item.setDescription(itemRequestDto.getDescription());
            item.setImageUrl(itemRequestDto.getItemImage());
            itemService.update(item);
        }
    }

    @Override
    public void delete(Long id) {
        itemService.delete(id);
    }

    @Override
    public ItemResponseDto findById(Long id) {
        Item item = itemService.findById(id).get();
        return new ItemResponseDto(item);
    }

    @Override
    public PageData<ItemResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Item> tableResponse = itemService.findAll(dataTableRequest);
        List<ItemResponseDto> items = tableResponse.getItems().stream().
                map(ItemResponseDto::new).
                collect(Collectors.toList());

        PageData<ItemResponseDto> pageData = (PageData<ItemResponseDto>) WebUtil.initPageData(tableResponse);
        pageData.setItems(items);
        return pageData;
    }

    @Override
    public PageData<ItemResponseDto> findAllByManufacturerId(WebRequest request, Long manufacturerId) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Item> tableResponse = itemService.findAll(dataTableRequest);
        List<ItemResponseDto> books = tableResponse.getItems().stream().
                map(ItemResponseDto::new).
                collect(Collectors.toList());

        PageData<ItemResponseDto> pageData = (PageData<ItemResponseDto>) WebUtil.initPageData(tableResponse);
        pageData.setItems(books);
        return pageData;
    }
}
