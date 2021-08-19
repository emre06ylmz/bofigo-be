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
public class ProductMaterialServiceInput extends BaseServiceInput {

	private double amount;

	private int rawMaterialId;

	private int rawMaterialCategoryId;

	private int productId;

	private RawMaterialServiceInput rawMaterial;

	private RawMaterialCategoryServiceInput rawMaterialCategory;

	private ProductServiceInput product;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getRawMaterialId() {
		return rawMaterialId;
	}

	public void setRawMaterialId(int rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}

	public int getRawMaterialCategoryId() {
		return rawMaterialCategoryId;
	}

	public void setRawMaterialCategoryId(int rawMaterialCategoryId) {
		this.rawMaterialCategoryId = rawMaterialCategoryId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public RawMaterialServiceInput getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(RawMaterialServiceInput rawMaterial) {
		this.rawMaterial = rawMaterial;
	}

	public RawMaterialCategoryServiceInput getRawMaterialCategory() {
		return rawMaterialCategory;
	}

	public void setRawMaterialCategory(RawMaterialCategoryServiceInput rawMaterialCategory) {
		this.rawMaterialCategory = rawMaterialCategory;
	}

	public ProductServiceInput getProduct() {
		return product;
	}

	public void setProduct(ProductServiceInput product) {
		this.product = product;
	}

}
