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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
