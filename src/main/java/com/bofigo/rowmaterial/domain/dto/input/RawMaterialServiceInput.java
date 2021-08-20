package com.bofigo.rowmaterial.domain.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RawMaterialServiceInput extends BaseServiceInput {

	private String name;

	private String explanation;

	private int rawMaterialCategoryId;

	private int unitId;

	private RawMaterialCategoryServiceInput rawMaterialCategory;

	private UnitServiceInput unit;

	private double stock;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public int getRawMaterialCategoryId() {
		return rawMaterialCategoryId;
	}

	public void setRawMaterialCategoryId(int rawMaterialCategoryId) {
		this.rawMaterialCategoryId = rawMaterialCategoryId;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public RawMaterialCategoryServiceInput getRawMaterialCategory() {
		return rawMaterialCategory;
	}

	public void setRawMaterialCategory(RawMaterialCategoryServiceInput rawMaterialCategory) {
		this.rawMaterialCategory = rawMaterialCategory;
	}

	public UnitServiceInput getUnit() {
		return unit;
	}

	public void setUnit(UnitServiceInput unit) {
		this.unit = unit;
	}

	public double getStock() {
		return stock;
	}

	public void setStock(double stock) {
		this.stock = stock;
	}

}
