package com.bofigo.rowmaterial.api.response;

import java.util.Date;

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
public class ProductionApiResponse extends BaseApiResponse {

	private double count;

	private String explanation;

	private Date date;

	private ProductApiResponse product;

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ProductApiResponse getProduct() {
		return product;
	}

	public void setProduct(ProductApiResponse product) {
		this.product = product;
	}

}
