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
public class UserApiRequest {

	private String username;

	private String password;

	private String name;

	private String surname;

	private String role;

}
