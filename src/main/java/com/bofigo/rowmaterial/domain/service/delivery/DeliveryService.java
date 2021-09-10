package com.bofigo.rowmaterial.domain.service.delivery;

import java.util.List;

import com.bofigo.rowmaterial.domain.dto.input.DeliveryServiceInput;
import com.bofigo.rowmaterial.domain.dto.output.DeliveryServiceOutput;
import com.bofigo.rowmaterial.domain.service.Service;
import com.bofigo.rowmaterial.exception.DataAlreadyExistException;
import com.bofigo.rowmaterial.exception.DataNotFoundException;

public interface DeliveryService extends Service {

	DeliveryServiceOutput getDeliveryById(Integer id) throws DataNotFoundException;

	DeliveryServiceOutput getDeliveryByProductId(Integer productId) throws DataNotFoundException;

	DeliveryServiceOutput createDelivery(DeliveryServiceInput deliveryServiceInput)
			throws DataAlreadyExistException;

	DeliveryServiceOutput updateDelivery(Integer id, DeliveryServiceInput deliveryServiceInput)
			throws DataNotFoundException;

	DeliveryServiceOutput deleteDelivery(Integer id) throws DataNotFoundException;

	List<DeliveryServiceOutput> listAll();

	List<DeliveryServiceOutput> listByProductId(Integer productId);

}
