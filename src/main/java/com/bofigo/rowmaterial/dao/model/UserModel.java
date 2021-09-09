package com.bofigo.rowmaterial.dao.model;

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
public class UserModel extends BaseModel {

	@Column(length = 100, nullable = false, unique = true)
	private String username;

	@Column(length = 100, nullable = false)
	private String password;
	
	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 100, nullable = false)
	private String surname;

	@Column(length = 100, nullable = false)
	private String role;

}