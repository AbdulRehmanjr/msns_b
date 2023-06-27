package com.msns.management.service;

import java.util.List;

import com.msns.management.model.Section;

public interface SectionService {
    

    Section createSection(Section section);

    List<Section> getAllSections();

    List<Section> getSectionByName(String sectionName);

    Section updateSection(Section section);
}
