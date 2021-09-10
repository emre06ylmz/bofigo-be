package com.bofigo.rowmaterial.api.response;

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
public class DeliveryApiResponse extends BaseApiResponse {

	private double count;

	private String explanation;

	private Date date;

	private ProductApiResponse product;

}
