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
public class RawMaterialServiceInput extends BaseServiceInput {

	private String name;

	private String explanation;

	private int rawMaterialCategoryId;

	private int unitId;

	private RawMaterialCategoryServiceInput rawMaterialCategory;

	private UnitServiceInput unit;

	private double stock;

	private String selectedCurrency;
	
	private double lastPrice;

}
