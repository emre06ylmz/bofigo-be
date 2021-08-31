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
public class ProductionApiResponse extends BaseApiResponse {

	private double amount;

	private RawMaterialApiResponse rawMaterial;

	private RawMaterialCategoryApiResponse rawMaterialCategory;

	private ProductApiResponse product;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public RawMaterialApiResponse getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(RawMaterialApiResponse rawMaterial) {
		this.rawMaterial = rawMaterial;
	}

	public RawMaterialCategoryApiResponse getRawMaterialCategory() {
		return rawMaterialCategory;
	}

	public void setRawMaterialCategory(RawMaterialCategoryApiResponse rawMaterialCategory) {
		this.rawMaterialCategory = rawMaterialCategory;
	}

	public ProductApiResponse getProduct() {
		return product;
	}

	public void setProduct(ProductApiResponse product) {
		this.product = product;
	}

}
