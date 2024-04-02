package com.chrisjhkim.sweethome.item.repository;

import com.chrisjhkim.sweethome.item.entity.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {


//	public Long
}
