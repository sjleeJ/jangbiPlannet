package com.plannet.www.store;

import com.plannet.www.BaseEntity;
import com.plannet.www.address.Address;
import com.plannet.www.category.Category;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "store")
public class Store extends BaseEntity {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String businessNumber;

	@Embedded
	private Address address;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

}
