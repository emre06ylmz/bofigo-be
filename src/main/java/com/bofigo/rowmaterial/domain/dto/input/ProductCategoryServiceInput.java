package com.bofigo.rowmaterial.domain.dto.input;

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
public class ProductCategoryServiceInput extends BaseServiceInput {

	private String name;

	private String explanation;
	
}
