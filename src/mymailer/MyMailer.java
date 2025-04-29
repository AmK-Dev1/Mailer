/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mymailer;

import javax.swing.SwingUtilities;
import mymailer.view.MainView;
import mymailer.view.HomeView;
import mymailer.view.HeaderView;
import mymailer.util.DataBase.IDbConnector;
import mymailer.util.DataBase.SqlServerConnector;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import mymailer.dao.UserDAO;
import mymailer.view.LoginForm;



public class MyMailer {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IDbConnector connector = new SqlServerConnector("AMK", "Mailer");

            MainView mainView = new MainView();
            LoginForm loginForm = new LoginForm(connector);

            mainView.setView(loginForm);
            mainView.setVisible(true);

        });
    }
}



