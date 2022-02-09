package com.alevel.facade;

import com.alevel.web.dto.response.ItemPLPDto;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

public interface PLPFacade {

    List<ItemPLPDto> search(WebRequest webRequest);
}
