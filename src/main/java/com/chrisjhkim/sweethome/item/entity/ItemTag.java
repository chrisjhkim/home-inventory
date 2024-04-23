package com.chrisjhkim.sweethome.item.entity;

import jakarta.persistence.*;

@Entity
public class ItemTag {

	@Id @GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@ManyToOne
	@JoinColumn(name = "tag_id")
	private Tag tag;
}
