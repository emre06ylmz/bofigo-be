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
public class RawMaterialServiceOutput extends BaseServiceOutput {

	private String name;

	private String explanation;

	private RawMaterialCategoryServiceOutput rawMaterialCategory;

	private UnitServiceOutput unit;

	private double stock;

	private String selectedCurrency;

}
