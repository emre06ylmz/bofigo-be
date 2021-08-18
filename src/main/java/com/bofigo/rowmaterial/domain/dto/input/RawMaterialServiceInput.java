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

	private RawMaterialCategoryServiceInput rawMaterialCategoryServiceInput;

	private UnitServiceInput unitServiceInput;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public int getRawMaterialCategoryId() {
		return rawMaterialCategoryId;
	}

	public void setRawMaterialCategoryId(int rawMaterialCategoryId) {
		this.rawMaterialCategoryId = rawMaterialCategoryId;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public RawMaterialCategoryServiceInput getRawMaterialCategoryServiceInput() {
		return rawMaterialCategoryServiceInput;
	}

	public void setRawMaterialCategoryServiceInput(RawMaterialCategoryServiceInput rawMaterialCategoryServiceInput) {
		this.rawMaterialCategoryServiceInput = rawMaterialCategoryServiceInput;
	}

	public UnitServiceInput getUnitServiceInput() {
		return unitServiceInput;
	}

	public void setUnitServiceInput(UnitServiceInput unitServiceInput) {
		this.unitServiceInput = unitServiceInput;
	}

}
