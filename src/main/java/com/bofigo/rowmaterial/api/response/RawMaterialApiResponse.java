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
public class RawMaterialApiResponse extends BaseApiResponse {

	private String name;

	private String explanation;

	private RawMaterialCategoryApiResponse rawMaterialCategory;

	private UnitApiResponse unit;

	private double stock;

	private String selectedCurrency;

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

	public String getSelectedCurrency() {
		return selectedCurrency;
	}

	public void setSelectedCurrency(String selectedCurrency) {
		this.selectedCurrency = selectedCurrency;
	}
}
