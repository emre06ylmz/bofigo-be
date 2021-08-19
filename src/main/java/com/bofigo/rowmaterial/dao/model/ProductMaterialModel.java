package com.bofigo.rowmaterial.dao.model;

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
public class ProductMaterialModel extends BaseModel {

	@Id
	@Column(unique = true, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "raw_material_id", referencedColumnName = "id", nullable = false)
	private RawMaterialModel rawMaterial;

	@ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "raw_material_category_id", referencedColumnName = "id", nullable = false)
	private RawMaterialCategoryModel rawMaterialCategory;

	@ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
	private ProductModel product;

	@Column(nullable = false)
	private double amount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RawMaterialModel getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(RawMaterialModel rawMaterial) {
		this.rawMaterial = rawMaterial;
	}

	public RawMaterialCategoryModel getRawMaterialCategory() {
		return rawMaterialCategory;
	}

	public void setRawMaterialCategory(RawMaterialCategoryModel rawMaterialCategory) {
		this.rawMaterialCategory = rawMaterialCategory;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
