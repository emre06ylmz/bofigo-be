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
public class PurchaseServiceInput extends BaseServiceInput {

	private double amount;

	private double price;

	private Date date;

	private String explanation;

	private int rawMaterialId;

	private int supplierId;

	private RawMaterialServiceInput rawMaterial;

	private SupplierServiceInput supplier;

}
