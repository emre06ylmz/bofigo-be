package com.bofigo.rowmaterial.dao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RawMaterialModel extends BaseModel {

	@Column(length = 100, nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "raw_material_category_id", referencedColumnName = "id", nullable = false)
	private RawMaterialCategoryModel rawMaterialCategory;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "unit_id", referencedColumnName = "id", nullable = false)
	private UnitModel unit;

	@Column(length = 100, nullable = false)
	private String explanation;

	@OneToMany(mappedBy = "rawMaterial", cascade = CascadeType.ALL)
	private List<PurchaseModel> purchases;

	@Column
	private double stock;

	@Column(length = 10, nullable = false, name = "selectedCurrency")
	private String selectedCurrency;

}
