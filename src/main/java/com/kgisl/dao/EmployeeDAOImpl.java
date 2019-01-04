package com.kgisl.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import com.kgisl.model.Employee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 

 
@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
   
    @Autowired
     private SessionFactory sessionFactory;
   
    
    public void addEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(employee);
    }
 
    
    public void updateEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.update(employee);
    }
 
    @SuppressWarnings("unchecked")
    
    public List<Employee> listEmployees() {
        Session session = sessionFactory.getCurrentSession();
        List<Employee> EmployeesList = session.createQuery("from Employee").list();
        System.out.println("EmployeeDAOImpl list="+EmployeesList);
        return EmployeesList;
        // TypedQuery<Employee> query = sessionFactory.getCurrentSession().createQuery("from Employee");
        // return query.getResultList();
    }
 
    
    public Employee getEmployeeById(int id) {
        Session session = sessionFactory.getCurrentSession();      
        Employee employee = (Employee) session.get(Employee.class, new Integer(id));
        System.out.println("EmployeeDAOImpl get="+employee);
        return employee;
    }
 
    
    public void removeEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = (Employee) session.get(Employee.class, new Integer(id));
        if(null != employee){
            session.delete(employee);
        }
    }
}