/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.sampleapp.controller;

import com.leapfrog.sampleapp.dao.EmployeeDAO;
import com.leapfrog.sampleapp.dao.EmployeeSalaryDAO;
import com.leapfrog.sampleapp.entity.Employee;
import com.leapfrog.sampleapp.entity.EmployeeSalary;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class EmployeeController {

    private EmployeeDAO employeeDAO;
    private EmployeeSalaryDAO empSalarayDAO;
    private Scanner input;

    public EmployeeController(EmployeeDAO employeeDAO, Scanner input) {
        this.employeeDAO = employeeDAO;
        this.input = input;

    }

    public void setEmpSalarayDAO(EmployeeSalaryDAO empSalarayDAO) {
        this.empSalarayDAO = empSalarayDAO;
    }

    public void menu() {
        System.out.println("1. Add Employee");
        System.out.println("2. Show All employees");
        System.out.println("3. Add Salary");
        System.out.println("4. Report");
        System.out.println("5. SalarySheet by Emp Code:");
        System.out.println("6. Export Salary Report");
        System.out.println("8. Exit");
        System.out.println("Enter your choice: ");
    }

    public void addView() {

        while (true) {
            Employee emp = new Employee();
            emp.setId(employeeDAO.insertId());
            System.out.println("Enter Code: ");
            emp.setCode(input.next());
            System.out.println("Enter First Name: ");
            emp.setFirstName(input.next());
            System.out.println("Enter Last Name: ");
            emp.setLastName(input.next());
            System.out.println("Enter Email: ");
            emp.setEmail(input.next());
            System.out.println("Enter Contact No: ");
            emp.setContactNo(input.next());
            System.out.println("Enter Salary: ");
            emp.setSalary(input.nextInt());

            emp.setStatus(true);

            employeeDAO.insert(emp);
            System.out.println("Do you want to add more[Y/N]: ");
            if (input.next().equalsIgnoreCase("n")) {
                break;
            }

        }

    }

    public void showEmployeeView() {
        System.out.println("--------------------");
        System.out.println("Employees");
        System.out.println("--------------------");
        for (Employee e : employeeDAO.getAll()) {
            System.out.println("Id:" + e.getId());
            System.out.println("Code:" + e.getCode());
            System.out.println("Name:" + e.getFullName());
            System.out.println("Email:" + e.getEmail());
            System.out.println("ContactNo:" + e.getContactNo());
            System.out.println("Salary:" + e.getSalary());
            System.out.println("Status:" + e.isStatus());
            System.out.println("--------------------");

        }
    }

    public void addSalaryView() {
        while (true) {
            System.out.println("Enter Employee Code: ");
            Employee emp = employeeDAO.getByCode(input.next());

            if (emp == null) {
                System.out.println("Employee code not found");

            } else {
                System.out.println("Code: " + emp.getCode());
                System.out.println("Name: " + emp.getFullName());
                System.out.println("Salary: " + emp.getSalary());
                System.out.println("Do you really want to enter salary[Y/N]");
                if (input.next().equalsIgnoreCase("y")) {
                    EmployeeSalary empSalary = new EmployeeSalary();
                    empSalary.setEmployee(emp);
                    empSalary.setSalary(emp.getSalary());
                    empSalary.setSalaryDate(new Date());
                    empSalarayDAO.insert(empSalary);
                }
            }
            System.out.println("Do you want to add another[Y/N]: ");
            if (input.next().equalsIgnoreCase("n")) {
                break;
            }
        }
    }

    public void salaryReportView() {
        System.out.println("---------------------");
        System.out.println("        Salary Report");
        System.out.println("---------------------");
        for (EmployeeSalary es : empSalarayDAO.getAll()) {
            Employee e = es.getEmployee();
            System.out.print(e.getCode() + "\t");
            System.out.print(e.getFullName() + "\t");
            System.out.print(es.getSalaryDate() + "\t");
            System.out.println(es.getSalary());
            System.out.println("-------------------");

        }
    }

    public void exportSalaryView() {
        try {
            System.out.println("Enter File Name:");
            String file = input.next();
            FileWriter writer = new FileWriter(file);
            System.out.println("Export into file " + file);
            StringBuilder content = new StringBuilder();

            for (EmployeeSalary es : empSalarayDAO.getAll()) {
                Employee e = es.getEmployee();
                content.append(e.getCode()).append(", ");
                content.append(e.getFullName()).append(", ");
                content.append(es.getSalary()).append(", ");
                content.append(es.getSalaryDate()).append(" \r\n");

            }
            writer.write(content.toString());
            writer.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public void process() {
        menu();
        switch (input.nextInt()) {
            case 1:
                addView();
                break;
            case 2:
                showEmployeeView();
                break;
            case 3:
                addSalaryView();
                break;
            case 4:
                salaryReportView();
                break;
            case 6:
                exportSalaryView();
                break;
            case 8:
                System.exit(0);
                break;
        }

    }

}
