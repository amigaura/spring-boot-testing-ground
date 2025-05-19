package com.testing.ground.sync.repository;

import com.testing.ground.sync.entity.ItemRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRecordRepository extends JpaRepository<ItemRecord, Long> {}