package com.bofigo.rowmaterial.api.request;

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
public class BaseApiRequest {

	private String createdBy;
	private String updatedBy;
	
}
