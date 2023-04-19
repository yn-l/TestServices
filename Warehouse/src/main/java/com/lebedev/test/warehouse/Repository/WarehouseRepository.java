package com.lebedev.test.warehouse.Repository;

import com.lebedev.test.warehouse.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<ProductEntity, Long> {


    List<ProductEntity> findByName(String name);
}
