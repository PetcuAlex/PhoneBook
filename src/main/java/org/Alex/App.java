package org.Alex;

import org.Alex.persistence.ContactRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        ContactRepository contactRepository = new ContactRepository();
        Scanner in = new Scanner(System.in);
//        contactRepository.createContact();
//        contactRepository.searchContact();
        System.out.println("What operation do you want to do?");
        String[] operations = new String[5];
        operations[0] = "1.add contact";
        operations[1] = "2.show contacts";
        operations[2] = "3.delete contact";
        operations[3] = "4.search contact";
        operations[4] = "5.edit contact";

        for (int i = 0; i <= operations.length - 1; i++) {
            System.out.println(operations[i]);
        }

        System.out.println("Enter a number:");
        int response = in.nextInt();

//        while (response > 0 & response <= 5) {
            switch (response) {
                case 1:
                    contactRepository.createContact();
                    break;
                case 2:
                    System.out.println(contactRepository.getContacts());
                    break;
                case 3:
                    contactRepository.deleteContact();
                    break;
                case 4:
                    contactRepository.searchContact();
                    break;
                case 5:
                    contactRepository.updateContact();
                    break;
                default:
                    System.out.println("Invalid operation, try again!");
            }

        }
    }
//}
