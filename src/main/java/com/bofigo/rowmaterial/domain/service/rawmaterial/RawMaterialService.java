package com.bofigo.rowmaterial.domain.service.rawmaterial;

import java.util.List;

import com.bofigo.rowmaterial.domain.dto.input.RawMaterialServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.RawMaterialServiceOutput;
import com.bofigo.rowmaterial.domain.service.Service;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;

public interface RawMaterialService extends Service {

	RawMaterialServiceOutput getRawMaterialById(Integer id) throws DataNotFoundException;

	RawMaterialServiceOutput getRawMaterialByName(String name);

	RawMaterialServiceOutput createRawMaterial(RawMaterialServiceInput rawMaterialServiceInput)
			throws DataAlreadyExistException;

	RawMaterialServiceOutput updateRawMaterial(Integer id, RawMaterialServiceInput rawMaterialServiceInput)
			throws DataNotFoundException;

	RawMaterialServiceOutput deleteRawMaterial(Integer id) throws DataNotFoundException;

	List<RawMaterialServiceOutput> listAll();

}
