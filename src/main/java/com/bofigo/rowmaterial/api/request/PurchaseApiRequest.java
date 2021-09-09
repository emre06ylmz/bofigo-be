package com.bofigo.rowmaterial.api.request;

import java.util.Date;

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
public class PurchaseApiRequest {

	private Date date;

	@NotNull(message = "Explanation cannot be null.")
	@Size(min = 3, max = 50, message = "Explanation must be 1-200 characters long.")
	private String explanation;

	@NotNull(message = "Amount cannot be null.")
	private double price;

	@NotNull(message = "Amount cannot be null.")
	private double amount;

	private int rawMaterialId;

	private int supplierId;

}
