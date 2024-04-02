package com.chrisjhkim.sweethome.item.service;

import com.chrisjhkim.sweethome.item.dto.ItemSearchCond;
import com.chrisjhkim.sweethome.item.entity.Item;
import com.chrisjhkim.sweethome.item.web.form.ItemDTO;

import java.util.List;

public interface ItemService {

	Item createItem(Item item);


	List<Item> findItems(ItemSearchCond itemSearchCond);

	List<Item> findAllItems();

	Item findById(Long itemId);

	void update(Long itemId, ItemDTO item);
}
