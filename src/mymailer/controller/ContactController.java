/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mymailer.controller;

import java.util.ArrayList;
import java.util.List;
import mymailer.model.Contact;

public class ContactController {
    
    private List<Contact> contacts;

    public ContactController() {
        contacts = new ArrayList<>();
    }
    
    public void addContact(Contact contact){
        contacts.add(contact);
    }

    public List<Contact> getContacts(){
        return contacts;
    }
}
