package net.proselyte.crudapp.controller;

import net.proselyte.crudapp.model.Speciality;
import net.proselyte.crudapp.repository.SpecialityRepository;
import net.proselyte.crudapp.repository.gson.GsonSpecialityRepositoryImpl;

import java.util.List;

public class SpecialityController {

    private final SpecialityRepository specialityRepository = new GsonSpecialityRepositoryImpl();

    public Speciality createSpeciality (String name) {
        Speciality speciality = new Speciality();
        speciality.setSpecialityName(name);
        return specialityRepository.save(speciality);
    }
    public List<Speciality> getAllSpecialities (){
        return specialityRepository.getAll();
    }

    public Speciality getSpecialityByID (Long id){
        return specialityRepository.getByID(id);
    }

    public Speciality updateSpeciality (Long id, String newSpeciality){
        List<Speciality> allSpecialities = getAllSpecialities();
        Speciality speciality = allSpecialities.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
        if (speciality != null) {
            speciality.setSpecialityName(newSpeciality);
            return specialityRepository.update(speciality);
        }
        throw new RuntimeException("Speciality with ID = " + id + "not found");
    }

    public void deleteSpecialityByID (Long id){
        specialityRepository.deleteById(id);
    }
}

