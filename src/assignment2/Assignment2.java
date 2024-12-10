/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment2;

/**
 *
 * @author Ziadw
 */
public class Assignment2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        HashTable phoneBook = new HashTable();
        
        phoneBook.insert("Zyad", "123-456-7890");
        phoneBook.insert("Jane Smith", "234-567-8901");
        phoneBook.insert("Alice Brown", "345-678-9012");
        
        String phone = phoneBook.search("Jane Smith");
        System.out.println("Phone number for Jane Smith: " + phone);

        // Step 4: Update a contact's phone number
        phoneBook.update("Alice Brown", "456-789-0123");

        // Step 5: Display all contacts
        System.out.println("All Contacts:");
        phoneBook.displayAllContacts();

        // Step 6: Delete a contact
        phoneBook.delete("John Doe");

        // Step 7: Display all contacts after deletion
        System.out.println("Contacts after deletion:");
        phoneBook.displayAllContacts();
    }
    
}
