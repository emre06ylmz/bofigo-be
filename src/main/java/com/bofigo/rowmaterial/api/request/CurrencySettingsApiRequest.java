package com.bofigo.rowmaterial.api.request;

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
public class CurrencySettingsApiRequest {

	private double dollar;
	private double euro;
	private Date lastUpdateDate;

	public double getDollar() {
		return dollar;
	}

	public void setDollar(double dollar) {
		this.dollar = dollar;
	}

	public double getEuro() {
		return euro;
	}

	public void setEuro(double euro) {
		this.euro = euro;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

}