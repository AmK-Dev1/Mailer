/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mymailer.model;

/**
 *
 * @author medkh
 */
public class User {
    
    private int ID;
    private String username;
    private String password;
    
    public User(){};
    
    public User(int ID){
    this.ID = ID;
    };
    
    public User(int ID, String username){
    this.ID = ID;
    this.username = username;
    };
    
    public User(int ID, String username, String password){
    this.ID = ID;
    this.username = username;
    this.password = password;
    };
    
    // most used
    public User(String username, String password){
    this.username = username;
    this.password = password;
    };
    
    // Getters and setters
    public int getId() { return ID; }
    public void setId(int id) { this.ID = ID; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    @Override
    public String toString() {
        return username;
    }
}
