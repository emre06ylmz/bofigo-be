	package com.bofigo.rowmaterial.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class ProductModel extends BaseModel {

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 100, nullable = false)
	private String explanation;

	@Column(nullable = false)
	private double cost_TL;

	@Column(nullable = false)
	private double cost_Total;

	@Column(nullable = false)
	private double cargo;
	
	@Column(nullable = false)
	private double tax;

	@Column(nullable = false)
	private double stock;
	
	@Column(length = 100, nullable = false)
	private String barcode;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "product_category_id", referencedColumnName = "id", nullable = false)
	private ProductCategoryModel productCategory;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "product_model_code_id", referencedColumnName = "id", nullable = false)
	private ProductModelCodeModel productModelCode;

}
