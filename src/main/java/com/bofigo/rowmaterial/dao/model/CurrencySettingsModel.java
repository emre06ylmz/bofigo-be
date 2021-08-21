package com.bofigo.rowmaterial.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class CurrencySettingsModel extends BaseModel {

	@Column(nullable = false)
	private double dollar;

	@Column(nullable = false)
	private double euro;

	@Column(nullable = false)
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