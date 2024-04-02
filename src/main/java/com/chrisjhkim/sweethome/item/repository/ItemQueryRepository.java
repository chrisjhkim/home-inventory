package com.chrisjhkim.sweethome.item.repository;

import com.chrisjhkim.sweethome.item.dto.ItemSearchCond;
import com.chrisjhkim.sweethome.item.entity.Item;
import com.chrisjhkim.sweethome.item.entity.QItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.chrisjhkim.sweethome.item.entity.QItem.item;

@Repository
@Slf4j
public class ItemQueryRepository {
//	@PersistenceContext
//	private EntityManager entityManager;
	private JPAQueryFactory query;

	public ItemQueryRepository(EntityManager em) {
		this.query = new JPAQueryFactory(em);
	}


	public List<Item> findAll(ItemSearchCond itemSearchCond){

		String itemName = itemSearchCond.getItemName();
		String placeName = itemSearchCond.getPlaceName();
		BooleanBuilder builder = new BooleanBuilder();

		if ( StringUtils.hasText(itemName))
			builder.or(item.itemName.like("%"+itemName+"%"));

		if ( StringUtils.hasText(placeName))
			builder.or(item.placeCode.like("%"+placeName+"%"));

		log.info("builder = {}", builder);

		return query
				.select(item)
				.from(QItem.item)
				.where( builder.or(null) )
				.fetch();

	}

}
