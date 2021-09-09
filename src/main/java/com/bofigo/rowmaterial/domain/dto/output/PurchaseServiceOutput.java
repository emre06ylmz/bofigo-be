package com.bofigo.rowmaterial.domain.dto.output;

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
public class PurchaseServiceOutput extends BaseServiceOutput {

	private double amount;

	private double price;

	private Date date;

	private String explanation;

	private RawMaterialServiceOutput rawMaterial;

	private SupplierServiceOutput supplier;

	private String selectedCurrency;

}
