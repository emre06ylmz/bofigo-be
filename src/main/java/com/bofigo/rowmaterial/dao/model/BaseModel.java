package com.bofigo.rowmaterial.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.bofigo.rowmaterial.util.StringUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public abstract class BaseModel implements Serializable {

	private static final long serialVersionUID = -6349295907409000764L;
	
	@Id
	@Column(unique = true, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String status;

	@Column
	private Date createDate;

	@Column
	private Date updateDate;

	@Column
	private String createdBy;

	@Column
	private String updatedBy;

	@PrePersist
	public void prePersist() {
		this.createDate = new Date();
		this.updateDate = new Date();

		this.createdBy = StringUtil.isNullOrEmpty(this.createdBy) ? "DEFAULT" : this.createdBy;
		this.updatedBy = StringUtil.isNullOrEmpty(this.updatedBy) ? "DEFAULT" : this.updatedBy;

		this.status = StringUtil.isNullOrEmpty(this.status) ? "ACTIVE" : this.status;
	}

	@PreUpdate
	public void preUpdate() {
		this.updateDate = new Date();
		this.updatedBy = StringUtil.isNullOrEmpty(this.updatedBy) ? "DEFAULT" : this.updatedBy;

		this.status = StringUtil.isNullOrEmpty(this.status) ? "ACTIVE" : this.status;
	}

}
