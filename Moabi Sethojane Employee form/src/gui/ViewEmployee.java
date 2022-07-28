/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Employee;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author SDMC-User4
 */
public class ViewEmployee extends JFrame{
    JTextArea taView  =new JTextArea();

    public ViewEmployee(ArrayList<Employee> arEmp) {
       taView.setEditable(false);
        setVisible(true);
        setSize(300, 500);
        Container scn = getContentPane();
        scn.setLayout(new BorderLayout());
        scn.add(taView,BorderLayout.CENTER);
        displayEmployee(arEmp);
    }
    public void displayEmployee(ArrayList<Employee>arEmp){
        String aname = JOptionPane.showInputDialog("Enter Employee Name ");
        if(aname.isEmpty()){
            arEmp = Employee.getAll();
        }else{
            arEmp = Employee.getAll(aname);
        }
        taView.append("No Data found "+arEmp.size()+"\n");
        taView.append("Employee Name\t"+"Annual Salary\t"+"Monthly Salary\t"+"Salary Increase"+"\n");
        for(int i =0;i<arEmp.size();i++){
            taView.append(arEmp.get(i).toString()+"\n");
        }
    }
    
    
}
