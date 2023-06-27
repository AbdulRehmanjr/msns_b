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
public class SectionServuceImp implements SectionService {

    @Autowired
    private SectionRepository sectionRepo;

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

    @Override
    public List<Section> getAllSections() {
        
        return this.sectionRepo.findAll();
    }

    @Override
    public List<Section> getSectionByName(String sectionName) {
        
        List<Section> section = this.sectionRepo.findBySectionName(sectionName);

        if(section == null || section.size() == 0) {
            log.error("Could not find section");
            return null;
        }

        return section;
            
    }

    @Override
    public Section updateSection(Section section) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSection'");
    }

}
