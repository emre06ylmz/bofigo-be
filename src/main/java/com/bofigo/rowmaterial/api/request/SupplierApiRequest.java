package com.bofigo.rowmaterial.api.request;

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
public class SupplierApiRequest {

	@NotNull(message = "Name cannot be null.")
	@Size(min = 3, max = 50, message = "Name must be 3-50 characters long.")
	private String name;

	@NotNull(message = "Explanation cannot be null.")
	@Size(min = 3, max = 50, message = "Explanation must be 1-200 characters long.")
	private String explanation;

}
