package com.alevel.persistence.repository.item;

import com.alevel.persistence.entity.item.Item;
import com.alevel.persistence.entity.manufacturer.Manufacturer;
import com.alevel.persistence.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends BaseRepository<Item> {

    List<Item> findByManufacturer(Optional<Manufacturer> manufacturer);
    List<Item> findByNameContaining(String name);

    @Query(value = "select * from items i " +
            "join category_item ci on i.id = ci.item_id " +
            "join categories cat on ci.category_id = cat.id " +
            "where cat.id = ?", nativeQuery = true)
    List<Item> findByCategories(Long id);
}
