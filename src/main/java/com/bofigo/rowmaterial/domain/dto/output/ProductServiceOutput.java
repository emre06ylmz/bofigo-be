package com.bofigo.rowmaterial.domain.dto.output;

import com.bofigo.rowmaterial.api.response.ProductCategoryApiResponse;
import com.bofigo.rowmaterial.api.response.ProductModelCodeApiResponse;

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
public class ProductServiceOutput extends BaseServiceOutput {

	private String name;

	private String explanation;

	private double cost_TL;

	private double cost_Total;

	private double cargo;
	
	private double tax;
	
	private double stock;
	
	private String barcode;

	private ProductCategoryServiceOutput productCategory;

	private ProductModelCodeServiceOutput productModelCode;
}
