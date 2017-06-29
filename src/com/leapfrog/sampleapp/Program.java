/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.sampleapp;

import com.leapfrog.sampleapp.controller.EmployeeController;
import com.leapfrog.sampleapp.dao.impl.EmployeeDAOImpl;
import com.leapfrog.sampleapp.dao.impl.EmployeeSalaryDAOImpl;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        EmployeeController ec = new EmployeeController(new EmployeeDAOImpl(), input);
        ec.setEmpSalarayDAO(new EmployeeSalaryDAOImpl());

        while (true) {
            ec.process();

        }

    }

}
