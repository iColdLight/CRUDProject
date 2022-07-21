package net.proselyte.crudapp.view;

import net.proselyte.crudapp.controller.DeveloperController;
import net.proselyte.crudapp.model.Developer;

import java.util.Scanner;

public class DeveloperView {
    private final DeveloperController developerController = new DeveloperController();
    private final Scanner scanner = new Scanner(System.in);

    public void createDeveloper() {
        System.out.println("Enter developer First name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter developer Last name: ");
        String lastName = scanner.nextLine();
        Developer developer = developerController.createDeveloper(firstName, lastName);
        System.out.println("Created developer: " + developer);
    }

    public void getAllDevelopers (){
        developerController.getAllDevelopers();
    }

    public void getDeveloperByID (){
        System.out.println("Enter developer ID: ");
        Long id = scanner.nextLong();
        developerController.getDeveloperByID(id);
    }

    public void updateDeveloper(){
        System.out.println("Enter developer id you want to update: ");
        Long id = scanner.nextLong();
        System.out.println("Enter new First name: ");
        String newFirstName = scanner.nextLine();
        System.out.println("Enter new Last name: ");
        String newLastName = scanner.nextLine();
        developerController.updateDeveloper(id, newFirstName, newLastName);
    }

    public void deleteDeveloperByID(){
        System.out.println("Enter developer ID you want to delete: ");
        Long id = scanner.nextLong();
        developerController.deleteDeveloperByID(id);
    }
}
