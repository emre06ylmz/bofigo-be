package com.bofigo.rowmaterial.domain.dto.output;

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
public class ProductServiceOutput extends BaseServiceOutput {

	private String name;

	private String explanation;

	private double cost_TL;

	private double cost_USD;

	private double cost_EURO;

}
