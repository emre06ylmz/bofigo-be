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
public class ProductMaterialApiResponse extends BaseApiResponse {

	private double amount;

	private RawMaterialApiResponse rawMaterial;

	private RawMaterialCategoryApiResponse rawMaterialCategory;

	private ProductApiResponse product;

}
