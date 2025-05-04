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
import mymailer.DAO.UserDAO;
import mymailer.view.LoginForm;



public class MyMailer {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IDbConnector connector = new SqlServerConnector("AMK", "MyMailer");

            MainView mainView = new MainView();
            LoginForm loginForm = new LoginForm(connector, mainView);

            mainView.setView(loginForm);
            mainView.setVisible(true);

        });
    }
}



