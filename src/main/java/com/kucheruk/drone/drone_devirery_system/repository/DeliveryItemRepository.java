package com.kucheruk.drone.drone_devirery_system.repository;

import com.kucheruk.drone.drone_devirery_system.model.DeliveryItem;
import com.kucheruk.drone.drone_devirery_system.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryItemRepository extends JpaRepository<DeliveryItem, Long> {
    List<DeliveryItem> findAllByDeliveryId(Long deliveryId);

    @Query("SELECT di.item FROM DeliveryItem di WHERE di.delivery.id = :deliveryId")
    List<Item> findAllItemsByDeliveryId(@Param("deliveryId") Long deliveryId);


}
