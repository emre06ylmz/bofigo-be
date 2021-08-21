package com.bofigo.rowmaterial.api.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class RawMaterialApiRequest {

	@NotNull(message = "Name cannot be null.")
	@Size(min = 3, max = 50, message = "Name must be 3-50 characters long.")
	private String name;

	@NotNull(message = "Explanation cannot be null.")
	@Size(min = 3, max = 50, message = "Explanation must be 1-200 characters long.")
	private String explanation;

	private int rawMaterialCategoryId;

	private int unitId;

	@NotNull(message = "Stock cannot be null.")
	private double stock;

	@NotNull(message = "Currency cannot be null.")
	@Size(min = 1, max = 10, message = "Currency must be 1-10 characters long.")
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
