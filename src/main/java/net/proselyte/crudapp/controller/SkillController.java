package net.proselyte.crudapp.controller;

import net.proselyte.crudapp.model.Skill;
import net.proselyte.crudapp.repository.SkillRepository;
import net.proselyte.crudapp.repository.gson.GsonSkillRepositoryImpl;

import java.util.List;

public class SkillController {

    private final SkillRepository skillRepository = new GsonSkillRepositoryImpl();

    public Skill createSkill (String name) {
        Skill skill = new Skill();
        skill.setName(name);
        return skillRepository.save(skill);
    }

    public List<Skill> getAllSkills (){
        return skillRepository.getAll();
    }

    public Skill getSkillByID (Long id){
        return skillRepository.getByID(id);
    }

    public Skill updateSkill (String oldSkill, String newSkill){
        List<Skill> allSkills = getAllSkills();
        allSkills.forEach(s -> {
            if (s.getName().equals(oldSkill)) {
                s.setName(newSkill);
            }
        });
        return skillRepository.update();
    }

    public void deleteSkillByID (Long id){
        skillRepository.deleteById(id);
    }
}

