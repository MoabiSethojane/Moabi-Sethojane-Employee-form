/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 *
 * @author SDMC-User4
 */
public class Employee implements Serializable{
private String empName,gender;
private double annualSalary,salaryPerc;

    public Employee() {
        empName="";
        gender= "";
        annualSalary =0;
        salaryPerc =0;
    }

    public Employee(String empName, String gender, double annualSalary, double salaryPerc) {
        this.empName = empName;
        this.gender = gender;
        this.annualSalary = annualSalary;
        this.salaryPerc = salaryPerc;
    }

    public String getEmpName() {
        return empName;
    }

    public String getGender() {
        return gender;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public double getSalaryPerc() {
        return salaryPerc;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public void setSalaryPerc(double salaryPerc) {
        this.salaryPerc = salaryPerc;
    }
public double monthlySalary(){
    return annualSalary/12;
}
public double salaryIncrease(){
    return annualSalary+(annualSalary*salaryPerc/100);
}
public String toString(){
    NumberFormat curr = NumberFormat.getCurrencyInstance();
    return  empName+"\t"+curr.format(annualSalary)+"\t"+curr.format(monthlySalary())+"\t"+curr.format(salaryIncrease());
}
 public static void initialize()throws DataStorageException{
     EmployeeDA.initialize();
 }
  public static void terminate()throws DataStorageException{
      EmployeeDA.terminate();
  }
      public void addEmployee()throws DuplicateException{
         EmployeeDA.addEmployee(this);
      }
       public void updateEmployee(String upEmp)throws NotFoundException{
         EmployeeDA.updateEmployee(this, upEmp);
       }
        public void deleteEmployee()throws NotFoundException{
         EmployeeDA.deleteEmployee(this);
        }
         public static Employee searchEmployee(String srchEmp)throws NotFoundException{
             return EmployeeDA.searchEmployee(srchEmp);
         }
          public static ArrayList<Employee>getAll(String strEmp){
              return  EmployeeDA.getAll(strEmp);
          }
           public static ArrayList<Employee>getAll(){
               return EmployeeDA.getAll();
           }
}
