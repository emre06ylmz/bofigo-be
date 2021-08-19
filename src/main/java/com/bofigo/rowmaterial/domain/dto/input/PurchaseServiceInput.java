package com.bofigo.rowmaterial.domain.dto.input;

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
public class PurchaseServiceInput extends BaseServiceInput {

	private double amount;

	private double price;

	private Date date;

	private String explanation;

	private int rawMaterialId;

	private int supplierId;

	private RawMaterialServiceInput rawMaterial;

	private SupplierServiceInput supplier;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

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

	public int getRawMaterialId() {
		return rawMaterialId;
	}

	public void setRawMaterialId(int rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public RawMaterialServiceInput getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(RawMaterialServiceInput rawMaterial) {
		this.rawMaterial = rawMaterial;
	}

	public SupplierServiceInput getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierServiceInput supplier) {
		this.supplier = supplier;
	}

}
