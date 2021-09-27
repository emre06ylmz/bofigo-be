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
public class RawMaterialApiResponse extends BaseApiResponse {

	private String name;

	private String explanation;

	private RawMaterialCategoryApiResponse rawMaterialCategory;

	private UnitApiResponse unit;

	private double stock;

	private String selectedCurrency;
	
	private double lastPrice;

}
