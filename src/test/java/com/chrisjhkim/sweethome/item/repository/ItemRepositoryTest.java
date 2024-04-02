package com.chrisjhkim.sweethome.item.repository;

import com.chrisjhkim.sweethome.item.entity.Item;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ItemRepositoryTest {
	@Autowired ItemRepository itemRepository;
	@Autowired TestEntityManager entityManager;

	@Test
	@DisplayName("save")
	@Transactional
	void testSave(){
	    // Given
		Item item = Item.builder()
				.quantity(10)
				.itemName("tissue")
				.build();
	    // When
		itemRepository.save(item);

	    // Then
		assertThat(item.getId()).isNotNull();
	}

	@Test
	@DisplayName("findAll")
	void test(){
	    // Given
		Item item1 = Item.builder()
				.quantity(10)
				.itemName("tissue")
				.build();
		entityManager.persist(item1);
		Item item2 = Item.builder()
				.quantity(5)
				.itemName("egg")
				.build();
		entityManager.persist(item2);

		entityManager.flush();

	    // When
		List<Item> list = itemRepository.findAll();

		// Then
		assertThat(list)
				.hasSize(2)
				.contains(item1, item2);
	}
}