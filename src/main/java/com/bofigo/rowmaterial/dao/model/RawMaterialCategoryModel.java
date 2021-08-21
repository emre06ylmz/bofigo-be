package com.bofigo.rowmaterial.dao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class RawMaterialCategoryModel extends BaseModel {

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 100, nullable = false)
	private String explanation;

	@OneToMany(mappedBy = "rawMaterialCategory", cascade = CascadeType.ALL)
	private List<RawMaterialModel> rawMaterials;

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

	public List<RawMaterialModel> getRawMaterials() {
		return rawMaterials;
	}

	public void setRawMaterials(List<RawMaterialModel> rawMaterials) {
		this.rawMaterials = rawMaterials;
	}

}
