package com.chrisjhkim.sweethome.item.service.impl;

import com.chrisjhkim.sweethome.item.dto.ItemSearchCond;
import com.chrisjhkim.sweethome.item.entity.Item;
import com.chrisjhkim.sweethome.item.repository.ItemQueryRepository;
import com.chrisjhkim.sweethome.item.repository.ItemRepository;
import com.chrisjhkim.sweethome.item.service.ItemService;
import com.chrisjhkim.sweethome.item.web.form.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {
	private final ItemRepository itemRepository;
	private final ItemQueryRepository itemQueryRepository;




	@Override
	public Item createItem(Item item) {
		Item savedItem = itemRepository.save(item);
		return savedItem;
	}


	@Override
	public List<Item> findItems(ItemSearchCond itemSearchCond) {
		return itemQueryRepository.findAll(itemSearchCond);
	}

	@Override
	public List<Item> findAllItems() {
		return itemRepository.findAll();
	}

	@Override
	public Item findById(Long itemId) {
		if ( itemId == null ) throw new IllegalArgumentException("input param itemId is null");

		return itemRepository.findById(itemId)
				.orElseThrow(() -> new IllegalStateException("item not found - itemId="+itemId));
	}

	@Override
	@Transactional
	public void update(Long itemId, ItemDTO itemUpdateRequest) {
		Item item = itemRepository.getReferenceById(itemId);

		item.update(itemUpdateRequest);


	}


}
