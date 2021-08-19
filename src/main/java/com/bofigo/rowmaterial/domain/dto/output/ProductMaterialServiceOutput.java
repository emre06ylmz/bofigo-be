package com.bofigo.rowmaterial.domain.dto.output;

import java.util.Date;

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
public class ProductMaterialServiceOutput extends BaseServiceOutput {

	private double amount;

	private RawMaterialServiceOutput rawMaterial;

	private RawMaterialCategoryServiceOutput rawMaterialCategory;
	
	private ProductServiceOutput product;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public RawMaterialServiceOutput getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(RawMaterialServiceOutput rawMaterial) {
		this.rawMaterial = rawMaterial;
	}

	public RawMaterialCategoryServiceOutput getRawMaterialCategory() {
		return rawMaterialCategory;
	}

	public void setRawMaterialCategory(RawMaterialCategoryServiceOutput rawMaterialCategory) {
		this.rawMaterialCategory = rawMaterialCategory;
	}

	public ProductServiceOutput getProduct() {
		return product;
	}

	public void setProduct(ProductServiceOutput product) {
		this.product = product;
	}


}
