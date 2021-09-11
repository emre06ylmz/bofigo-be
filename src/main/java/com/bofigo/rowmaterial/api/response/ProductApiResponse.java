package com.bofigo.rowmaterial.api.response;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class ProductApiResponse extends BaseApiResponse {

	private String name;

	private String explanation;

	private double cost_TL;

	private double cost_USD;

	private double cost_EURO;

	private double cargo;
	
	private double tax;
	
	private double stock;
	
	private String barcode;

	private ProductCategoryApiResponse productCategory;

	private ProductModelCodeApiResponse productModelCode;
}
