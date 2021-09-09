package com.bofigo.rowmaterial.domain.dto.output;

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
public class ProductMaterialServiceOutput extends BaseServiceOutput {

	private double amount;

	private RawMaterialServiceOutput rawMaterial;

	private RawMaterialCategoryServiceOutput rawMaterialCategory;

	private ProductServiceOutput product;

}
