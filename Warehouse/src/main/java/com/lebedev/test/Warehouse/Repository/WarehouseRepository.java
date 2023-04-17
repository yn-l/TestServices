package com.lebedev.test.Warehouse.Repository;

import com.lebedev.test.Warehouse.Model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<ProductEntity, Long> {


    List<ProductEntity> findByName(String name);
}
