package com.msns.management.service;

import java.util.List;

import com.msns.management.model.Section;

public interface SectionService {

    /**
     * The createSection function creates a new section and returns it.
     * 
     * @param section The "section" parameter is an object of type Section. It
     *                represents the section
     *                that you want to create.
     * @return The createSection function returns a Section object.
     */
    Section createSection(Section section);

    /**
     * The function getAllSections returns a list of Section objects.
     * 
     * @return A List of Section objects is being returned.
     */
    List<Section> getAllSections();

    /**
     * The function "getSectionByName" returns a list of Section objects that match
     * a given section
     * name.
     * 
     * @param sectionName A string representing the name of the section to search
     *                    for.
     * @return The method is returning a list of Section objects.
     */
    List<Section> getSectionByName(String sectionName);

    /**
     * The function updateSection takes a Section object as input and returns an
     * updated Section
     * object.
     * 
     * @param section The "section" parameter is an object of type Section. It
     *                represents a section of
     *                a document or a specific part of a larger whole.
     * @return The method is returning a Section object.
     */
    Section updateSection(Section section);
}
