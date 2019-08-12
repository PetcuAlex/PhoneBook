package org.Alex.persistence;

import org.Alex.domain.Contact;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactRepository {

    public void createContact() throws SQLException, IOException, ClassNotFoundException {

        Scanner in = new Scanner(System.in);

        System.out.println("Enter first name");
        String firstName = in.nextLine();
        System.out.println("Enter last name");
        String lastName = in.nextLine();
        System.out.println("Enter phone number");
        int phoneNumber = in.nextInt();

        String sql = "INSERT INTO contacts(first_name,last_name, number) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, phoneNumber);

            preparedStatement.executeUpdate();
        }

    }

    public List<Contact> getContacts() throws SQLException, IOException, ClassNotFoundException {
        String query = "SELECT id,first_name,last_name,`number` FROM contacts";

        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(query);

            List<Contact> contacts = new ArrayList<>();

            while (resultSet.next()) {
                Contact item = new Contact();
                item.setId(resultSet.getLong("id"));
                item.setFirstName(resultSet.getString("first_name"));
                item.setLastName(resultSet.getString("last_name"));
                item.setPhoneNumber(resultSet.getInt("number"));

                contacts.add(item);

            }
            return contacts;
        }

    }

    public void deleteContact() throws SQLException, IOException, ClassNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println("What id you want to delete?");
        long id = in.nextLong();
        String sql = "DELETE FROM contacts WHERE id = ?";
        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }

    public void updateContact() throws SQLException, IOException, ClassNotFoundException {
        Scanner in = new Scanner(System.in);

        System.out.println("What id do you want to edit?");
        long id = in.nextLong();
        System.out.println("Enter first name");
        String firstName = in.next();
        System.out.println("Enter last name");
        String lastName = in.next();
        System.out.println("Enter phone number");
        int phoneNumber = in.nextInt();

        String sql = "UPDATE contacts SET first_name = ?, last_name = ?, `number` = ? WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, phoneNumber);
            preparedStatement.setLong(4, id);

            preparedStatement.executeUpdate();
        }

    }

    public void searchContact() throws SQLException, IOException, ClassNotFoundException {

        Scanner in = new Scanner(System.in);
////        String keyWord = "";
        System.out.println("You want to search by(enter number):");
        System.out.println("1.first name");
        System.out.println("2 last name");
        int response = in.nextInt();
        System.out.println("enter it:");
        String keyWord = in.next();
//
//        if (response == 1) {

        switch (response){

            case 1:
            String sql = "SELECT id, first_name, last_name,`number` FROM contacts WHERE first_name = ? ";

            try (Connection connection = DatabaseConfiguration.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ) {
//                System.out.println("Enter first name:");
//                String key = in.nextLine();
                preparedStatement.setString(1, keyWord);
                ResultSet resultSet = preparedStatement.executeQuery();


                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    int number = resultSet.getInt("number");

                    Contact contact = new Contact();
                    contact.setId(id);
                    contact.setFirstName(firstName);
                    contact.setLastName(lastName);
                    contact.setPhoneNumber(number);
                    System.out.println(contact);
                }


            }
            break;
            case 2:
                String sql2 = "SELECT id, first_name, last_name,`number` FROM contacts WHERE last_name = ? ";

                try (Connection connection = DatabaseConfiguration.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(sql2);
                ) {
//                System.out.println("Enter first name:");
//                String key = in.nextLine();
                    preparedStatement.setString(1, keyWord);
                    ResultSet resultSet = preparedStatement.executeQuery();


                    while (resultSet.next()) {
                        long id = resultSet.getLong("id");
                        String firstName = resultSet.getString("first_name");
                        String lastName = resultSet.getString("last_name");
                        int number = resultSet.getInt("number");

                        Contact contact = new Contact();
                        contact.setId(id);
                        contact.setFirstName(firstName);
                        contact.setLastName(lastName);
                        contact.setPhoneNumber(number);
                        System.out.println(contact);
                    }


                }
            break;
            default:
                System.out.println("invalid number");

        }
    }
}
