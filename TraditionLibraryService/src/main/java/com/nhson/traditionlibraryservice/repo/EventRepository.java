package com.nhson.traditionlibraryservice.repo;

import com.nhson.traditionlibraryservice.model.TraditionalEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<TraditionalEvent, Long>, JpaSpecificationExecutor<TraditionalEvent> {

    @EntityGraph(attributePaths = {"tags", "regions", "ritualSteps", "offerings", "media"})
    @Query("SELECT e FROM TraditionalEvent e WHERE e.id = :id")
    Optional<TraditionalEvent> findEventById(@Param("id") Long id);


    @EntityGraph(attributePaths = {"tags", "regions", "media", "offerings", "ritualSteps"})
    Page<TraditionalEvent> findAll(Specification<TraditionalEvent> spec, Pageable pageable);
}