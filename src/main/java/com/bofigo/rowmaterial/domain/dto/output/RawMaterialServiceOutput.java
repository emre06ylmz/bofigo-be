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

	private Integer id;
	private String name;
	private String explanation;

	private RawMaterialCategoryServiceOutput rawMaterialCategoryServiceOutput;
	private UnitServiceOutput unitServiceOutput;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public RawMaterialCategoryServiceOutput getRawMaterialCategoryServiceOutput() {
		return rawMaterialCategoryServiceOutput;
	}

	public void setRawMaterialCategoryServiceOutput(RawMaterialCategoryServiceOutput rawMaterialCategoryServiceOutput) {
		this.rawMaterialCategoryServiceOutput = rawMaterialCategoryServiceOutput;
	}

	public UnitServiceOutput getUnitServiceOutput() {
		return unitServiceOutput;
	}

	public void setUnitServiceOutput(UnitServiceOutput unitServiceOutput) {
		this.unitServiceOutput = unitServiceOutput;
	}

}
