/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

/**
 *
 * @author Ziadw
 */
public class DoublyNode {
    String name;
    String phoneNumber;
    DoublyNode next;
    DoublyNode prev;
    
     DoublyNode(String name, String phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.next = null;
            this.prev = null;
        }
}
