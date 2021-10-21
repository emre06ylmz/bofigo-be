package com.bofigo.rowmaterial.api.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class ProductApiRequest {

	@NotNull(message = "Name cannot be null.")
	@Size(min = 3, max = 50, message = "Name must be 3-50 characters long.")
	private String name;

	@NotNull(message = "Explanation cannot be null.")
	@Size(min = 3, max = 50, message = "Explanation must be 3-50 characters long.")
	private String explanation;

	@Size(min = 0, max = 500, message = "Image must be 0-50 characters long.")
	private String image;
	
	@NotNull(message = "Cost cannot be null.")
	private double cost_TL;

	@NotNull(message = "Sale cannot be null.")
	private double sale;
	
	@NotNull(message = "Cost cannot be null.")
	private double cost_Total;

	@NotNull(message = "Cargo cannot be null.")
	private double cargo;
	
	@NotNull(message = "Tax cannot be null.")
	private double tax;

	@NotNull(message = "Stock cannot be null.")
	private double stock;
	
	@NotNull(message = "Barcode cannot be null.")
	@Size(min = 3, max = 50, message = "Barcode must be 3-100 characters long.")
	private String barcode;

	private int productCategoryId;

	private int productModelCodeId;
}
