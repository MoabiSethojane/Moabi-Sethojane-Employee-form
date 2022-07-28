/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.DataStorageException;
import data.Employee;
import data.NotFoundException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author SDMC-User4
 */
public class MainMenuSreen extends JFrame {

    private static ArrayList<Employee> arEmp;
    private static final String emp_file = "empfile.dat";
    JMenuItem miAdd, miView, miSearch, miWriteToFile, miExit, miUpdate;
    JMenu mnFile, mnEmployee;

    public MainMenuSreen() {
        miAdd = new JMenuItem("Add Employee");
        miExit = new JMenuItem("Exit");
        miSearch = new JMenuItem("Search Employee");
        miView = new JMenuItem("View Employee");
        miUpdate = new JMenuItem("Update Employee");
        miWriteToFile = new JMenuItem("Write to File ");
        mnEmployee = new JMenu("Employee");
        mnFile = new JMenu("File");

        mnFile.add(miWriteToFile);
        mnFile.add(miExit);
        mnEmployee.add(miAdd);
        mnEmployee.add(miView);
        mnEmployee.add(miUpdate);
        mnEmployee.add(miSearch);
        
        JMenuBar jmb = new JMenuBar();
        setJMenuBar(jmb);
        jmb.add(mnFile);
        jmb.add(mnEmployee);
        miAdd.addActionListener(new addEvent());
        miView.addActionListener(new viewEvent());
        miExit.addActionListener(new exitEvent());
        miSearch.addActionListener(new searchEvent());
        miUpdate.addActionListener(new updateEvent());
        miWriteToFile.addActionListener(new writeTofile());
        addWindowListener(new writeListener());
        try{
            Employee.initialize();
        }catch(DataStorageException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    private class addEvent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           EmployeeGui frm = new EmployeeGui(arEmp);
           frm.setTitle("EMPLOYEE FORM");
           frm.setVisible(true);
           frm.setSize(500, 500);
        }
        
    }
    
    
    private class updateEvent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           String search = JOptionPane.showInputDialog("Enter Employee name to search");
           String upEmployee = JOptionPane.showInputDialog("Enter Employee Name to Update");
           try{
               Employee emp = Employee.searchEmployee(search);
               emp.deleteEmployee();
               
           }catch(NotFoundException e){
               JOptionPane.showMessageDialog(null, e.getMessage());
           }
        }
        
    }
private class viewEvent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            ViewEmployee frm = new ViewEmployee(arEmp);
            frm.setTitle("View Employee");
            frm.setVisible(true);
            frm.setSize(500, 500);
            
        }
    
}
private class searchEvent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           String srch = JOptionPane.showInputDialog("Enter Employee Name");
           try{
              Employee emp = Employee.searchEmployee(srch);
              JOptionPane.showMessageDialog(null,emp);
           }catch(NotFoundException e){
               JOptionPane.showMessageDialog(null,e.getMessage());
           }
        }
    
}
private class exitEvent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            int resp;
            try{
                resp= JOptionPane.showConfirmDialog(null,"Do you want to Exit ","Confermation",JOptionPane.YES_NO_OPTION);
                if(resp==JOptionPane.YES_OPTION){
                    Employee.terminate();
                    System.exit(0);
                }else{
                    mnEmployee.requestFocus();
                }
            }catch(DataStorageException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    
}
private class writeListener extends WindowAdapter{
    @Override
public  void windowClosing(WindowEvent we){
 readToFile();
}
}
private class writeTofile implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
          String empfile = "emp.txt";
          try{
             FileWriter fWriter = new FileWriter(empfile);
              PrintWriter pWriter = new PrintWriter(fWriter);
              for(int i = 0;i<arEmp.size();i++){
                  pWriter.println(arEmp.get(i).toString());
              }
              pWriter.close();
          }catch(IOException e){
              JOptionPane.showMessageDialog(null, e.getMessage());
          }  
         
            
        }
    
}
    public static void main(String[] args) {
        MainMenuSreen frm = new MainMenuSreen();
        frm.setTitle("Main Form ");
        frm.setSize(300, 500);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
     public void readFromFile(){
        try{
            FileInputStream fis = new FileInputStream(emp_file);
            ObjectInputStream objRead = new ObjectInputStream(fis);
            arEmp =(ArrayList<Employee>)objRead.readObject() ;
            objRead.close();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Fail to find file"+ e.getMessage());
            arEmp = new ArrayList<>();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public void readToFile(){
        try
        {
            FileOutputStream fos = new FileOutputStream(emp_file);
            ObjectOutputStream objWrite = new ObjectOutputStream(fos);
            objWrite.writeObject(arEmp);
            objWrite.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
