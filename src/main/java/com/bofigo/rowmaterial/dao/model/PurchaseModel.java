package com.bofigo.rowmaterial.dao.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class PurchaseModel extends BaseModel {

	@Column(length = 100, nullable = false)
	private Date date;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "raw_material_id", referencedColumnName = "id", nullable = false)
	private RawMaterialModel rawMaterial;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false)
	private SupplierModel supplier;

	@Column(length = 100, nullable = false)
	private String explanation;

	@Column(nullable = false)
	private double amount;

	@Column(nullable = false)
	private double price;

}
