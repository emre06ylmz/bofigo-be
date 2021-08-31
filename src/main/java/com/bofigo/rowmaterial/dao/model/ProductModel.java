package com.bofigo.rowmaterial.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;

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
public class ProductModel extends BaseModel {

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 100, nullable = false)
	private String explanation;

	@Column(nullable = false)
	private double cost_TL;

	@Column(nullable = false)
	private double cost_USD;

	@Column(nullable = false)
	private double cost_EURO;

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

	public double getCost_TL() {
		return cost_TL;
	}

	public void setCost_TL(double cost_TL) {
		this.cost_TL = cost_TL;
	}

	public double getCost_USD() {
		return cost_USD;
	}

	public void setCost_USD(double cost_USD) {
		this.cost_USD = cost_USD;
	}

	public double getCost_EURO() {
		return cost_EURO;
	}

	public void setCost_EURO(double cost_EURO) {
		this.cost_EURO = cost_EURO;
	}

}
