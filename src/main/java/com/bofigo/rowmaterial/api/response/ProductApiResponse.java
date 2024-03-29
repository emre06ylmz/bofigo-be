package com.bofigo.rowmaterial.api.response;

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

	private String image;

	private double cost_TL;

	private double cost_Plus;

	private double cost_PlusTax;

	private double sale;

	private double cost_Total;

	private double cargo;

	private double tax;

	private double stock;

	private String barcode;

	private ProductCategoryApiResponse productCategory;

	private ProductModelCodeApiResponse productModelCode;
}
