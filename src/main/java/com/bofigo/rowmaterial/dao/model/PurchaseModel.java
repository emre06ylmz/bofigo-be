package com.bofigo.rowmaterial.dao.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PurchaseModel extends BaseModel {

	@Id
	@Column(unique = true, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 100, nullable = false)
	private Date date;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "raw_material_id", referencedColumnName = "id", nullable = false)
	private RawMaterialModel rawMaterial;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false)
	private SupplierModel supplier;

	@Column(length = 100, nullable = false)
	private String explanation;

	@Column(nullable = false)
	private double amount;

	@Column(nullable = false)
	private double price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public RawMaterialModel getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(RawMaterialModel rawMaterial) {
		this.rawMaterial = rawMaterial;
	}

	public SupplierModel getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
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

}
