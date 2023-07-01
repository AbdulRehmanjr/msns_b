package com.msns.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msns.management.model.Section;

public interface SectionRepository extends JpaRepository<Section, Integer> {

    /**
     * The function findBySectionName returns a list of Section objects that match
     * the given
     * sectionName.
     * 
     * @param sectionName A string representing the name of the section to search
     *                    for.
     * @return The method findBySectionName returns a List of Section objects.
     */
    List<Section> findBySectionName(String sectionName);
}
