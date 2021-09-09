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
public class ProductionServiceOutput extends BaseServiceOutput {

	private double count;

	private int productId;

	private String explanation;

	private Date date;

	private ProductServiceOutput product;

}
