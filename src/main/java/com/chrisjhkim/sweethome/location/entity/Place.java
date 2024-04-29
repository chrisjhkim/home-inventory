package com.chrisjhkim.sweethome.location.entity;

import com.chrisjhkim.sweethome.item.entity.Item;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Entity
//@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class Place {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;



	@OneToMany(mappedBy = "place")
//	@Builder.Default
	private List<Item> items = new ArrayList<>();



	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Room room;

	@Builder
	Place(Long id, String name,
//	      List<Item> items,
	      Room room) {
		this.id = id;
		this.name = name;
		this.items = items;
		this.room = room;
		items.forEach(this::addItem);

	}

	public void addItem(Item item) {
		this.items.add(item);
	}
}
