/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mymailer.util.DataBase;

import java.sql.Connection;
import java.sql.SQLException;


public interface IDbConnector {
    
    Connection connect() throws SQLException;
    void disconnect() throws SQLException;
    
}