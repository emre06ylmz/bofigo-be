package com.bofigo.rowmaterial.domain.dto.output;

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
public class RawMaterialServiceOutput extends BaseServiceOutput {

	private String name;

	private String explanation;

	private RawMaterialCategoryServiceOutput rawMaterialCategory;

	private UnitServiceOutput unit;

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

	public RawMaterialCategoryServiceOutput getRawMaterialCategory() {
		return rawMaterialCategory;
	}

	public void setRawMaterialCategory(RawMaterialCategoryServiceOutput rawMaterialCategory) {
		this.rawMaterialCategory = rawMaterialCategory;
	}

	public UnitServiceOutput getUnit() {
		return unit;
	}

	public void setUnit(UnitServiceOutput unit) {
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