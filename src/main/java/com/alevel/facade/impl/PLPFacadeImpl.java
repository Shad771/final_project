package com.alevel.facade.impl;

import com.alevel.config.annotations.InjectLog;
import com.alevel.facade.PLPFacade;
import com.alevel.logger.LoggerLevel;
import com.alevel.logger.LoggerService;
import com.alevel.persistence.entity.item.Item;
import com.alevel.service.PLPService;
import com.alevel.util.WebUtil;
import com.alevel.web.dto.response.ItemPLPDto;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PLPFacadeImpl implements PLPFacade {

    @InjectLog
    private LoggerService loggerService;

    private final PLPService plpService;

    public PLPFacadeImpl(PLPService plpService) {
        this.plpService = plpService;
    }

    @Override
    public List<ItemPLPDto> search(WebRequest webRequest) {
        Map<String, Object> queryMap = new HashMap<>();
        if (webRequest.getParameterMap().get(WebUtil.MANUFACTURER_PARAM) != null) {
            String[] params = webRequest.getParameterMap().get(WebUtil.MANUFACTURER_PARAM);
            Long manufacturerId = Long.parseLong(params[0]);
            queryMap.put(WebUtil.MANUFACTURER_PARAM, manufacturerId);
            loggerService.commit(LoggerLevel.INFO, "add " + WebUtil.MANUFACTURER_PARAM + ": " + manufacturerId);
        }
        if (webRequest.getParameterMap().get(WebUtil.ITEM_SEARCH_PARAM) != null) {
            String[] params = webRequest.getParameterMap().get(WebUtil.ITEM_SEARCH_PARAM);
            String itemName = params[0];
            queryMap.put(WebUtil.ITEM_SEARCH_PARAM, itemName);
            loggerService.commit(LoggerLevel.INFO, "add " + WebUtil.ITEM_SEARCH_PARAM + ": " + itemName);
        }
        if (webRequest.getParameterMap().get(WebUtil.CATEGORY_SEARCH_PARAM) != null) {
            String[] params = webRequest.getParameterMap().get(WebUtil.CATEGORY_SEARCH_PARAM);
            Long category = Long.valueOf(params[0]);
            queryMap.put(WebUtil.CATEGORY_SEARCH_PARAM, category);
            loggerService.commit(LoggerLevel.INFO, "add " + WebUtil.CATEGORY_SEARCH_PARAM + ": " + category);
        }

        List<Item> items = plpService.search(queryMap);
        List<ItemPLPDto> itemPLPDtos = items.stream().map(ItemPLPDto::new).toList();
        return itemPLPDtos;
    }
}
