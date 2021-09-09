package com.bofigo.rowmaterial.domain.dto.input;

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
public class ProductMaterialServiceInput extends BaseServiceInput {

	private double amount;

	private int rawMaterialId;

	private int rawMaterialCategoryId;

	private int productId;

	private RawMaterialServiceInput rawMaterial;

	private RawMaterialCategoryServiceInput rawMaterialCategory;

	private ProductServiceInput product;

}
