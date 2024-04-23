package com.chrisjhkim.sweethome.item.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Tag {

	@Id @GeneratedValue
	private Long id;

	private String name;


	@OneToMany(mappedBy = "tag")
	private List<ItemTag> items = new ArrayList<>();
}
