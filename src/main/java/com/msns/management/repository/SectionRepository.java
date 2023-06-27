package com.msns.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msns.management.model.Section;

public interface SectionRepository extends JpaRepository<Section,Integer>{

    List<Section> findBySectionName(String sectionName);
}
