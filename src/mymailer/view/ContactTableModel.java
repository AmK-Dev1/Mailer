/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mymailer.view;
import mymailer.model.Contact;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author medkh
 */
public class ContactTableModel extends AbstractTableModel {
    
    private final List<Contact> contacts;
    private final String[] cols = {"ID","Name","Email","Created At"};
    private final DateTimeFormatter fmt = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ContactTableModel(List<Contact> contacts) {
        this.contacts = contacts;
    }
    
    @Override public int getRowCount()       { return contacts.size(); }
    @Override public int getColumnCount()    { return cols.length; }
    @Override public String getColumnName(int c) { return cols[c]; }

    @Override
    public Object getValueAt(int row, int col) {
        Contact c = contacts.get(row);
        switch(col) {
            case 0: return c.getId();
            case 1: return c.getName();
            case 2: return c.getEmail();
            case 3: return c.getCreatedAt().format(fmt);
            default: return "";
        }
    }
    
}
