package com.bofigo.rowmaterial.dao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

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
public class ProductCategoryModel extends BaseModel {

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 100, nullable = false)
	private String explanation;

	@OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL)
	private List<ProductModel> products;

}
