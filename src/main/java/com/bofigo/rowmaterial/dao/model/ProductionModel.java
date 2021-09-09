package com.bofigo.rowmaterial.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class ProductionModel extends BaseModel {

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
	private ProductModel product;

	@Column(nullable = false)
	private double count;

	@Column(nullable = false)
	private Date date;

	@Column(length = 100, nullable = false)
	private String explanation;

}
