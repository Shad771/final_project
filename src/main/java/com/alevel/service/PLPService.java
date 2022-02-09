package com.alevel.service;

import com.alevel.persistence.entity.item.Item;

import java.util.List;
import java.util.Map;

public interface PLPService {
    List<Item> search(Map<String, Object> queryMap);
}
