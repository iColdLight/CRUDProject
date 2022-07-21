package net.proselyte.crudapp.controller;

import net.proselyte.crudapp.model.Developer;
import net.proselyte.crudapp.repository.DeveloperRepository;
import net.proselyte.crudapp.repository.gson.GsonDeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController {

    private final DeveloperRepository developerRepository = new GsonDeveloperRepositoryImpl();

    public Developer createDeveloper (String firstName, String lastName){
        Developer developer = new Developer();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        return developerRepository.save(developer);
    }

    public List<Developer> getAllDevelopers(){
        return developerRepository.getAll();
    }

    public Developer getDeveloperByID (Long id){
        return developerRepository.getByID(id);
    }

    public Developer updateDeveloper (Long id, String newFirstName, String newLastName){
        List<Developer> allDevelopers = getAllDevelopers();
        Developer developer = allDevelopers.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
        if (developer != null) {
            developer.setFirstName(newFirstName);
            developer.setLastName(newLastName);
            return developerRepository.update(developer);
        }
        throw new RuntimeException("Developer with ID = " + id + "not found");

    }

    public void deleteDeveloperByID (Long id){
        developerRepository.deleteById(id);
    }

}
