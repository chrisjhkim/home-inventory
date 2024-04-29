package com.chrisjhkim.sweethome.location.repository;

import com.chrisjhkim.sweethome.item.entity.Item;
import com.chrisjhkim.sweethome.item.web.form.ItemType;
import com.chrisjhkim.sweethome.location.entity.Place;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.awt.print.Book;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class PlaceRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PlaceRepository placeRepository;

	@Test
	public void testSavePlace() {
		// Test # Given
		Place place = Place.builder()
				.name("선반")
				.build();
		place = entityManager.persistAndFlush(place);

		// Test # When
		Place foundPlace = placeRepository.findById(place.getId()).orElse(null);

		// Test # Then
		assertThat(foundPlace).isNotNull();
		assertThat(foundPlace.getName()).isEqualTo("선반");
	}

	@Test
	public void test_placeItemMapping() {
		// Test # Given
		Place place = Place.builder()
				.name("선반")
				.build();
		place = entityManager.persistAndFlush(place);

		Item book = Item.builder()
				.itemName("스프링 부트책")
				.isNewItem(false)
				.itemType(ItemType.BOOK)
				.place(place)
				.build();
		entityManager.persistAndFlush(book);

		entityManager.clear();

		// Test # When
		Place foundPlace = placeRepository.findById(place.getId()).orElse(null);

		// Test # Then
		assertThat(foundPlace).isNotNull();
		System.out.println("foundPlace = " + foundPlace.getItems());
		assertThat(foundPlace.getName()).isEqualTo("선반");
		assertThat(foundPlace.getItems().get(0).getItemName()).isEqualTo("스프링 부트책");


	}

	@Test
	public void testFindAllPlaces() {
		Place place1 = Place.builder()
				.name("선반")
//				.items(List.of(item1))
				.build();
		Item item1 = Item.builder()
				.itemName("item1")
				.place(place1)
				.build();
		System.out.println("place1 = " + place1);


		Place place2 = Place.builder()
				.name("책장")
				.build();

		entityManager.persistAndFlush(place1);
		entityManager.persistAndFlush(place2);

		List<Place> places = placeRepository.findAll();
		assertThat(places).hasSize(2);
		assertThat(places.get(0).getName()).isEqualTo("선반");
		assertThat(places.get(1).getName()).isEqualTo("책장");
	}
}
