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
public class PurchaseApiResponse extends BaseApiResponse {

	private Date date;

	private String explanation;

	private double price;

	private double amount;

	private RawMaterialApiResponse rawMaterial;

	private SupplierApiResponse supplier;

}
