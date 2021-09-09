package com.bofigo.rowmaterial.api.request;

import javax.validation.constraints.NotNull;

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
public class ProductMaterialApiRequest {

	@NotNull(message = "Amount cannot be null.")
	private double amount;

	private int rawMaterialId;

	private int rawMaterialCategoryId;

	private int productId;

}
