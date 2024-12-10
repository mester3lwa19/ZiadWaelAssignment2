/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

/**
 *
 * @author Ziadw
 */
public class HashTable {
    private static int TABLE_SIZE = 1009;
    
    DoublyNode[] table;
    
    public HashTable(){
        this.table = new DoublyNode [TABLE_SIZE];
    }
    
    private int calcHash(String key) {
        int i, l = key.length();
        long hash = 0;
        for (i = 0; i < l; i++) {
            hash += Character.getNumericValue(key.charAt(i));
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
        
        return (int) Math.abs(hash % TABLE_SIZE);
    }
    
    public void insert(String name, String phoneNumber) {
        int index = calcHash(name);
        DoublyNode head = table[index];
        
        // Check if the contact already exists and update if found
        DoublyNode current = head;
        while (current != null) {
            if (current.name.equals(name)) {
                current.phoneNumber = phoneNumber;
                return;
            }
            current = current.next;
        }

        // Insert new node at the beginning of the doubly linked list
        DoublyNode newNode = new DoublyNode(name, phoneNumber);
        newNode.next = head;
        if (head != null) {
            head.prev = newNode;
        }
        table[index] = newNode;
    }
    
     // 2. Search for a contact by name
    public String search(String name) {
        int index = calcHash(name);
        DoublyNode current = table[index];
        
        while (current != null) {
            if (current.name.equals(name)) {
                return current.phoneNumber;
            }
            current = current.next;
        }
        return null; // Contact not found
    }
    
    public void delete(String name) {
        int index = calcHash(name);
        DoublyNode current = table[index];

        while (current != null) {
            if (current.name.equals(name)) {
                // Adjust pointers for deletion
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    table[index] = current.next; // Update head if deleting the first node
                }
                
                if (current.next != null) {
                    current.next.prev = current.prev;
                }
                return;
            }
            current = current.next;
        }
    }
    
    // 4. Update a contact's phone number
    public void update(String name, String newPhoneNumber) {
        int index = calcHash(name);
        DoublyNode current = table[index];
        
        while (current != null) {
            if (current.name.equals(name)) {
                current.phoneNumber = newPhoneNumber;
                return;
            }
            current = current.next;
        }
        
        // If contact does not exist, insert it as a new entry
        insert(name, newPhoneNumber);
    }
    
     // 5. Display all contacts
    public void displayAllContacts() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            DoublyNode current = table[i];
            while (current != null) {
                System.out.println("Name: " + current.name + ", Phone: " + current.phoneNumber);
                current = current.next;
            }
        }
    }

}
