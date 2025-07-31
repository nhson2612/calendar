package com.nhson.userservice.application.repository;

import com.nhson.userservice.application.model.CustomEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomEventRepository extends JpaRepository<CustomEvent,Long> {
}
