/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 *
 * @author SDMC-User4
 */
public class EmployeeDA {
    private static ArrayList<Employee> arEmp;
    private static final String empfile = "emp.dat";
    public static void initialize()throws DataStorageException{
        try{
            FileInputStream fis = new FileInputStream(empfile);
            ObjectInputStream objRead = new ObjectInputStream(fis);
            arEmp =(ArrayList<Employee>)objRead.readObject() ;
            objRead.close();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Fail to find file"+ e.getMessage());
            arEmp = new ArrayList<>();
        }catch(IOException e){
            throw new DataStorageException("fail to read file "+e.getMessage());
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static void terminate()throws DataStorageException{
        try
        {
            FileOutputStream fos = new FileOutputStream(empfile);
            ObjectOutputStream objWrite = new ObjectOutputStream(fos);
            objWrite.writeObject(arEmp);
            objWrite.close();
        }catch(IOException e){
            throw new DataStorageException("fail to write file "+e.getMessage());
        }
    }
    public static void addEmployee(Employee emp)throws DuplicateException{
        boolean duplicate = false;
        for(int i =0;i<arEmp.size()&&!duplicate;i++){
            if(arEmp.get(i).getEmpName().equals(emp.getEmpName())){
                duplicate = true;
            }
        }
        if(duplicate){
            throw new DuplicateException(emp.getEmpName()+" Already exist");
        }else{
            arEmp.add(emp);
        }
    }
    public static void updateEmployee(Employee emp , String upEmp)throws NotFoundException{
        boolean found = false;
        for(int i =0; i<arEmp.size()&&!found;i++){
            if(arEmp.get(i).getEmpName().equals(emp.getEmpName())){
                found = true;
                arEmp.get(i).setEmpName(upEmp);
            }
        }
        if(!found){
            throw new NotFoundException(emp.getEmpName()+" Does not exist");
        }
    }
    public static void deleteEmployee(Employee emp)throws NotFoundException{
        boolean found = false;
        for(int i=0;i<arEmp.size()&&!found;i++){
            if(arEmp.get(i).getEmpName().equals(emp.getEmpName())){
                found  = true;
                arEmp.remove(i);
                
            }
        }
        if(!found){
            throw new NotFoundException(emp.getEmpName()+" Does not Exist");
        }
    }
    public static Employee searchEmployee(String srchEmp)throws NotFoundException{
        Employee emp =null;
        boolean found = false;
        for(int i=0;i<arEmp.size()&&!found;i++){
            emp = arEmp.get(i);
            if(emp.getEmpName().equals(srchEmp)){
                found = true;
            }
        }
        if(found){
            return emp;
        }else{
            throw new NotFoundException(emp.getEmpName()+" Does not Exist");
        }
    }
    public static ArrayList<Employee>getAll(String strEmp){
       ArrayList arD = new ArrayList();
       for(Employee emp:arEmp){
           if(emp.getEmpName().startsWith(strEmp)){
               arD.add(emp);
           }
       }
       return  arD;
    }
    public static ArrayList<Employee>getAll(){
        return arEmp;
    }
}
