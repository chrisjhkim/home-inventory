package com.chrisjhkim.sweethome.item.controller;

import com.chrisjhkim.sweethome.item.entity.Item;
import com.chrisjhkim.sweethome.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemRestController {

	private final ItemService itemService;

//	@PostMapping("/item/create")
//	public Item createItem(@RequestBody Item item) {
//		return itemService.createItem(item);
//	}
//


}
