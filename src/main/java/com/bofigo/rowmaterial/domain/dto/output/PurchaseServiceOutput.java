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
public class PurchaseServiceOutput extends BaseServiceOutput {

	private Integer id;

	private double amount;

	private double price;

	private Date date;

	private String explanation;

	private RawMaterialServiceOutput rawMaterial;

	private SupplierServiceOutput supplier;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public RawMaterialServiceOutput getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(RawMaterialServiceOutput rawMaterial) {
		this.rawMaterial = rawMaterial;
	}

	public SupplierServiceOutput getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierServiceOutput supplier) {
		this.supplier = supplier;
	}

}
