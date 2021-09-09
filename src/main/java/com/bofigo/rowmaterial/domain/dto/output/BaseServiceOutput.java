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
public abstract class BaseServiceOutput implements Comparable<BaseServiceOutput> {

	private Integer id;
	
	private String createdBy;
	
	private String updatedBy;
	
	private Date createDate;
	
	private Date updatedDate;

	@Override
	public int compareTo(BaseServiceOutput baseServiceOutput) {
		int result = this.createDate.compareTo(baseServiceOutput.createDate);
		return result == 0 ? result : -result;
	}

}