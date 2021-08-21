package com.bofigo.rowmaterial.api.response;

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
public class PurchaseApiResponse extends BaseApiResponse {

	private Date date;

	private String explanation;

	private double price;

	private double amount;

	private RawMaterialApiResponse rawMaterial;

	private SupplierApiResponse supplier;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

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

	public SupplierApiResponse getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierApiResponse supplier) {
		this.supplier = supplier;
	}

}
