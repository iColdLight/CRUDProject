package net.proselyte.crudapp.view;

import net.proselyte.crudapp.controller.SkillController;
import net.proselyte.crudapp.model.Skill;

import java.util.Scanner;

public class SkillView {
    private final SkillController skillController = new SkillController();
    private final Scanner scanner = new Scanner(System.in);


    public void createSkill() {
        System.out.println("Enter skill name: ");
        String name = scanner.nextLine();
        Skill skill = skillController.createSkill(name);
        System.out.println("Created skill: " + skill);
    }

    public void getAllSkills (){
        skillController.getAllSkills();
    }

    public void getSkillByID (){
        System.out.println("Enter skill ID: ");
        Long id = scanner.nextLong();
        skillController.getSkillByID(id);

    }

    public void updateSkill (){
        System.out.println("Enter skill id you want to update: ");
        Long id = scanner.nextLong();
        System.out.println("Enter a new skill name: ");
        String newSkill = scanner.nextLine();
        skillController.updateSkill(id, newSkill);
    }

    public void deleteSkillByID(){
        System.out.println("Enter skill ID you want to delete: ");
        Long id = scanner.nextLong();
        skillController.deleteSkillByID(id);
    }
}
