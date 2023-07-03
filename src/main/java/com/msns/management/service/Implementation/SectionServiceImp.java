package com.msns.management.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msns.management.model.Section;
import com.msns.management.repository.SectionRepository;
import com.msns.management.service.SectionService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SectionServiceImp implements SectionService {

    @Autowired
    private SectionRepository sectionRepo;

    /**
     * The createSection function saves a Section object to the database and returns
     * the saved object,
     * or null if there was an error.
     * 
     * @param section The "section" parameter is an object of type Section that
     *                represents the section
     *                to be created.
     * @return The method is returning a Section object.
     */
    @Override
    public Section createSection(Section section) {

        try {

            Section response = this.sectionRepo.save(section);

            return response;
        } catch (Exception e) {

            log.error("Error while saving section: " + e.getMessage());
            return null;
        }

    }

    /**
     * The function returns a list of all sections.
     * 
     * @return The method is returning a List of Section objects.
     */
    @Override
    public List<Section> getAllSections() {

        return this.sectionRepo.findAll();
    }

    /**
     * The function `getSectionByName` retrieves a list of sections from the section
     * repository based
     * on the given section name.
     * 
     * @param sectionName The parameter "sectionName" is a String that represents
     *                    the name of the
     *                    section you want to retrieve.
     * @return The method is returning a List of Section objects.
     */
    @Override
    public List<Section> getSectionByName(String sectionName) {

        List<Section> section = this.sectionRepo.findBySectionName(sectionName);

        if (section == null || section.size() == 0) {
            log.error("Could not find section");
            return null;
        }

        return section;

    }

    /**
     * The function updateSection is not implemented and throws an
     * UnsupportedOperationException.
     * 
     * @param section The parameter "section" is an object of type Section, which
     *                represents a section
     *                in a document or a webpage.
     * @return The method is not implemented, so it throws an
     *         UnsupportedOperationException.
     */
    @Override
    public Section updateSection(Section section) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSection'");
    }

}
