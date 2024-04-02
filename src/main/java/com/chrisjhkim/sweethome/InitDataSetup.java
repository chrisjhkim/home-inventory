package com.chrisjhkim.sweethome;

import com.chrisjhkim.sweethome.item.entity.Item;
import com.chrisjhkim.sweethome.item.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDataSetup {
	private final ItemRepository itemRepository;

	@PostConstruct
	void postConstruct(){
		Item item1 = Item.builder()
				.itemName("tissue").quantity(10)
				.build();
		itemRepository.save(item1);

		Item item2 = Item.builder()
				.itemName("box").quantity(20)
				.build();
		itemRepository.save(item2);
	}
}
