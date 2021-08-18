package com.bofigo.rowmaterial.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RawMaterialModel extends BaseModel {

	@Id
	@Column(unique = true, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 100, nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "raw_material_category_id", referencedColumnName = "id", nullable = false)
	private RawMaterialCategoryModel rawMaterialCategoryModel;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "unit_id", referencedColumnName = "id", nullable = false)
	private UnitModel unitModel;

	@Column(length = 100, nullable = false)
	private String explanation;

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

	public RawMaterialCategoryModel getRawMaterialCategoryModel() {
		return rawMaterialCategoryModel;
	}

	public void setRawMaterialCategoryModel(RawMaterialCategoryModel rawMaterialCategoryModel) {
		this.rawMaterialCategoryModel = rawMaterialCategoryModel;
	}

	public UnitModel getUnitModel() {
		return unitModel;
	}

	public void setUnitModel(UnitModel unitModel) {
		this.unitModel = unitModel;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

}
