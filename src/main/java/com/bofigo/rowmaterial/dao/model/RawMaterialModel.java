package com.bofigo.rowmaterial.dao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	@Id
	@Column(unique = true, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RawMaterialCategoryModel getRawMaterialCategory() {
		return rawMaterialCategory;
	}

	public void setRawMaterialCategoryModel(RawMaterialCategoryModel rawMaterialCategory) {
		this.rawMaterialCategory = rawMaterialCategory;
	}

	public UnitModel getUnit() {
		return unit;
	}

	public void setUnitModel(UnitModel unit) {
		this.unit = unit;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public List<PurchaseModel> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<PurchaseModel> purchases) {
		this.purchases = purchases;
	}

	public void setRawMaterialCategory(RawMaterialCategoryModel rawMaterialCategory) {
		this.rawMaterialCategory = rawMaterialCategory;
	}

	public void setUnit(UnitModel unit) {
		this.unit = unit;
	}

	public double getStock() {
		return stock;
	}

	public void setStock(double stock) {
		this.stock = stock;
	}

}
