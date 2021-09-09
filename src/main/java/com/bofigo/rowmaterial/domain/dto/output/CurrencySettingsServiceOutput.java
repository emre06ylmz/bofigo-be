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
public class CurrencySettingsServiceOutput extends BaseServiceOutput {

	private double dollar;
	
	private double euro;
	
	private Date lastUpdateDate;

}
