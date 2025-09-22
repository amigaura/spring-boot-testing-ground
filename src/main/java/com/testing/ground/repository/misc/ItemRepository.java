package com.testing.ground.repository.misc;

import com.testing.ground.entity.misc.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
