/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.DuplicateException;
import data.Employee;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author SDMC-User4
 */
public class EmployeeGui extends JFrame{
    JTextField txtName = new JTextField(20);
    JTextField txtSalary = new JTextField(20);
    JLabel lblName = new JLabel("Employee Name");
    JLabel lblSalary = new JLabel("Annual Salary");
    JLabel lblgender = new JLabel("Gender");
    JComboBox<String>cmb = new JComboBox<>();
    JLabel lblperc = new JLabel("Salary Perc %");
    JRadioButton rdoMale = new JRadioButton("Male");
    JRadioButton rdoFemale = new JRadioButton("Female");
    JButton btnAdd =new JButton("Add Employee");
    JButton btnClear = new JButton("Clear");
    JButton btnExit = new JButton("Exit");
            private ArrayList<Employee>arEmp;

    public EmployeeGui(ArrayList<Employee> arEmp) {
        this.arEmp = arEmp;
        cmb.addItem("7");
        cmb.addItem("8");
        cmb.addItem("9");
        cmb.addItem("10");
        JPanel pnl = new JPanel();
        pnl.setLayout(null);
        setContentPane(pnl);
        pnl.add(txtName);
        pnl.add(txtSalary);
        pnl.add(lblName);
        pnl.add(lblSalary);
        pnl.add(lblperc);
        pnl.add(lblgender);
        pnl.add(cmb);
        pnl.add(rdoFemale);
        pnl.add(rdoMale);
        pnl.add(btnAdd);
        pnl.add(btnClear);
        pnl.add(btnExit);
        
        
         //pnl.add(tArea);
     //setting control bounds for labels
     lblName.setBounds(10, 20, 140, 20);
     lblgender.setBounds(10, 50, 140, 20);
     lblSalary.setBounds(10, 80, 140, 20);
     lblperc.setBounds(10, 110, 140, 20);
     //input bounds
     txtName.setBounds(100, 20, 120, 20);
     //radiobuttons in same line
     rdoMale.setBounds(100, 50, 100, 20);
     rdoFemale.setBounds(210, 50, 100, 20);
      txtSalary.setBounds(100, 80, 120, 20);
     cmb.setBounds(100, 110, 100, 20);
    
     
     
     //button bound
     btnAdd.setBounds(20, 160, 120, 20);
     btnClear.setBounds(180, 160, 120, 20);
     btnExit.setBounds(90, 190, 120, 20);
     
     //text area bound for output
//     tArea.setBounds(10, 230, 300, 250);
//     tArea.setEditable(false);
        setContentPane(pnl);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //handling listeners

        
        
        
        btnAdd.addActionListener(new addHandler());
        btnExit.addActionListener(new exitHandler());
        btnClear.addActionListener(new clearHandler());
        
    }
            private class addHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           try{
               Employee objEmp = getEmployee();
               objEmp.addEmployee();
           }catch(DuplicateException e){
               JOptionPane.showMessageDialog(null, e.getMessage());
           }
        }
                
            }
            private class clearHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           txtName.setText("");
           //request cursor to Employee text field
           txtName.requestFocus();
           txtSalary.setText("");
           cmb.setSelectedIndex(0);
           rdoFemale.setSelected(false);
           rdoMale.setSelected(false);
           
           
        }
                
            }
            private class exitHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            dispose();
        }
                
            }
            public Employee getEmployee(){
                Employee objEmp =null;
                String name,gender,salaryPerc;
                double salary,salaryInc;
                name = txtName.getText();
                salaryPerc = cmb.getSelectedItem().toString();
                salary= Double.parseDouble(txtSalary.getText());
                salaryInc = Double.parseDouble(salaryPerc);
                if(rdoMale.isSelected()){
                    gender = rdoMale.getText();
                    
                }else{
                    gender = rdoFemale.getText();
                }
                objEmp = new Employee(name, gender, salary, salaryInc);
                return objEmp;
            }
    
}
