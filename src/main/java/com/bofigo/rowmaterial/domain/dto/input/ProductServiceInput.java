package com.bofigo.rowmaterial.domain.dto.input;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bofigo.rowmaterial.dao.model.ProductCategoryModel;
import com.bofigo.rowmaterial.dao.model.ProductModelCodeModel;

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
public class ProductServiceInput extends BaseServiceInput {

	private String name;

	private String explanation;

	private String image;
	
	private double cost_TL;

	private double cost_Total;

	private double sale;
	
	private double cargo;
	
	private double tax;

	private double stock;
	
	private String barcode;
	
	private int productCategoryId;
	
	private int productModelCodeId;
}
