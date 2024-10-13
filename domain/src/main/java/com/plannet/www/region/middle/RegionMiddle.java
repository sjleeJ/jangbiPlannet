package com.plannet.www.region.middle;

import java.util.ArrayList;
import java.util.List;

import com.plannet.www.BaseEntity;
import com.plannet.www.region.big.RegionBig;
import com.plannet.www.region.small.RegionSmall;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "region_middle")
public class RegionMiddle extends BaseEntity {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "region_big_id")
	private RegionBig regionBig;

	@OneToMany(mappedBy = "region_small")
	private List<RegionSmall> regionSmallArrayList = new ArrayList<>();

	private String name;
}
