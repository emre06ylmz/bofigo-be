package com.bofigo.rowmaterial.domain.dto.input;

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
public class ProductionServiceInput extends BaseServiceInput {

	private double count;

	private int productId;

	private String explanation;

	private Date date;

	private ProductServiceInput product;

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	public ProductServiceInput getProduct() {
		return product;
	}

	public void setProduct(ProductServiceInput product) {
		this.product = product;
	}

}
