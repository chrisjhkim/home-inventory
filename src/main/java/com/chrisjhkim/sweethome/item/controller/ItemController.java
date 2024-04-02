package com.chrisjhkim.sweethome.item.controller;

import com.chrisjhkim.sweethome.item.dto.ItemSearchCond;
import com.chrisjhkim.sweethome.item.entity.Item;
import com.chrisjhkim.sweethome.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

	private final ItemService itemService;

//	@GetMapping("/items")
//	public String listItem(Model model
//			,
//	                       @ModelAttribute("itemSearch")ItemSearchCond itemSearchCond
//	){
//		log.info("itemSearchCond = {}", itemSearchCond);
//		List<Item> items = itemService.findAllItems();
//
//		model.addAttribute("items", items);
//		model.addAttribute("itemSearch", new ItemSearchCond());
//
//
//		return "items";
//	}
//
//	@GetMapping("/item/create")
//	public String showItemCreateForm(){
//		return "new-item";
//	}
//
//
//	@PostMapping("/item/create")
//	public String createItem(@RequestBody Item item) {
//		itemService.createItem(item);
//		return "redirect:/items";
//	}
//


}

