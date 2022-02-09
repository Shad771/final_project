package com.alevel.facade;

import com.alevel.web.dto.request.RequestDto;
import com.alevel.web.dto.response.PageData;
import com.alevel.web.dto.response.ResponseDto;
import org.springframework.web.context.request.WebRequest;

public interface CrudFacade<REQ extends RequestDto, RES extends ResponseDto> {

    void create(REQ req);
    void update(REQ req, Long id);
    void delete(Long id);
    RES findById(Long id);
    PageData<RES> findAll(WebRequest request);
}
