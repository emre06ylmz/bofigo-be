package com.bofigo.rowmaterial.domain.service;

import java.util.List;

import com.bofigo.rowmaterial.domain.dto.UnitServiceInput;
import com.bofigo.rowmaterial.domain.dto.UnitServiceOutput;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;

public interface UnitService extends Service {

	UnitServiceOutput getUnitById(Integer id) throws DataNotFoundException;

	UnitServiceOutput getUnitByName(String name);

	UnitServiceOutput createUnit(
			UnitServiceInput UnitServiceInput) throws DataAlreadyExistException;

	UnitServiceOutput updateUnit(Integer id,
			UnitServiceInput UnitServiceInput) throws DataNotFoundException;

	UnitServiceOutput deleteUnit(Integer id) throws DataNotFoundException;

	List<UnitServiceOutput> listAll();

}
