package net.proselyte.crudapp.view;

import net.proselyte.crudapp.controller.SpecialityController;
import net.proselyte.crudapp.model.Speciality;
import java.util.Scanner;

public class SpecialityView {
    private final SpecialityController specialityController = new SpecialityController();
    private final Scanner scanner = new Scanner(System.in);

    public void createSpeciality() {
        System.out.println("Enter speciality name: ");
        String name = scanner.nextLine();
        Speciality speciality = specialityController.createSpeciality(name);
        System.out.println("Created speciality: " + speciality);
    }

    public void getAllSpecialities (){
        specialityController.getAllSpecialities();
    }

    public void getSpecialityByID (){
        System.out.println("Enter speciality ID: ");
        Long id = scanner.nextLong();
        specialityController.getSpecialityByID(id);

    }

    public void updateSpeciality (){
        System.out.println("Enter speciality ID you want to update: ");
        Long id = scanner.nextLong();
        System.out.println("Enter a new speciality name: ");
        String newSpeciality = scanner.nextLine();
        specialityController.updateSpeciality(id, newSpeciality);
    }

    public void deleteSpecialityByID(){
        System.out.println("Enter speciality ID you want to delete: ");
        Long id = scanner.nextLong();
        specialityController.deleteSpecialityByID(id);
    }
}
