package com.bofigo.rowmaterial.api.response;

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
public class RawMaterialApiResponse {

	private Integer id;

	private String name;

	private String explanation;

	private RawMaterialCategoryApiResponse rawMaterialCategory;

	private UnitApiResponse unit;

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

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public RawMaterialCategoryApiResponse getRawMaterialCategory() {
		return rawMaterialCategory;
	}

	public void setRawMaterialCategory(RawMaterialCategoryApiResponse rawMaterialCategory) {
		this.rawMaterialCategory = rawMaterialCategory;
	}

	public UnitApiResponse getUnit() {
		return unit;
	}

	public void setUnit(UnitApiResponse unit) {
		this.unit = unit;
	}

	public double getStock() {
		return stock;
	}

	public void setStock(double stock) {
		this.stock = stock;
	}

}
