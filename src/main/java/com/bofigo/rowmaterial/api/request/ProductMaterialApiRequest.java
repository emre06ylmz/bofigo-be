package com.bofigo.rowmaterial.api.request;

import javax.validation.constraints.NotNull;

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
public class ProductMaterialApiRequest {

	@NotNull(message = "Amount cannot be null.")
	private double amount;

	private int rawMaterialId;

	private int rawMaterialCategoryId;

	private int productId;

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

}
