package com.bofigo.rowmaterial.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bofigo.rowmaterial.dao.model.DeliveryModel;

@Repository
public interface DeliveryRepository
		extends JpaRepository<DeliveryModel, Integer>, JpaSpecificationExecutor<DeliveryModel> {

	@Override
	Optional<DeliveryModel> findById(Integer id);

	Optional<DeliveryModel> findByProductId(Integer productId);

	@Override
	void deleteAll();

	@Query("select r from DeliveryModel r where r.product.id=:productId")
	List<DeliveryModel> listByProductId(Integer productId);

}
