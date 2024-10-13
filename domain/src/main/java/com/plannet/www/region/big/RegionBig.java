package com.plannet.www.region.big;

import java.util.ArrayList;
import java.util.List;

import com.plannet.www.BaseEntity;
import com.plannet.www.region.middle.RegionMiddle;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "region_big")
public class RegionBig extends BaseEntity {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@OneToMany(mappedBy = "region_middle")
	private List<RegionMiddle> regionMiddleList = new ArrayList<>();
}
