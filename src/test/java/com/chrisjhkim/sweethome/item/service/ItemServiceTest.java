package com.chrisjhkim.sweethome.item.service;

import com.chrisjhkim.sweethome.item.entity.Item;
import com.chrisjhkim.sweethome.item.repository.ItemRepository;
import com.chrisjhkim.sweethome.item.service.impl.ItemServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class ItemServiceTest {

	@Mock
	private ItemRepository itemRepository;

	@InjectMocks
	private ItemServiceImpl itemService;

	@Test
	@DisplayName("create Item")
	void testCreateItem(){
	    // Given
		Item item = Item.builder()
				.quantity(10)
				.itemName("tissue")
				.build();

		given(itemRepository.save(item)).willReturn(item);

		// When
		Item savedItem = itemService.createItem(item);

		// Then
		assertThat(savedItem).isEqualTo(item);
	}


	@Test
	@DisplayName("findAll")
	void testFindAll(){
	    // Given
		Item item1 = Item.builder()
				.quantity(10)
				.itemName("tissue")
				.build();
		Item item2 = Item.builder()
				.quantity(10)
				.itemName("box")
				.build();
		given(itemRepository.findAll()).willReturn(List.of(item1, item2));

		// When
		List<Item> allItems = itemService.findAllItems();

		// Then
		assertThat(allItems).containsExactly(item1, item2);
	}

}