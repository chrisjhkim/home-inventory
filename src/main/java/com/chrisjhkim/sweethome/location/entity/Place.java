package com.chrisjhkim.sweethome.location.entity;

import com.chrisjhkim.sweethome.item.entity.Item;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;


	@OneToMany(mappedBy = "place")
	private List<Item> items = new ArrayList<>();
}
