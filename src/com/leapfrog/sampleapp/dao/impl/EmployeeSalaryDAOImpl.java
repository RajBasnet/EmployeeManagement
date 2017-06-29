/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.sampleapp.dao.impl;

import com.leapfrog.sampleapp.entity.EmployeeSalary;
import java.util.List;
import com.leapfrog.sampleapp.dao.EmployeeSalaryDAO;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class EmployeeSalaryDAOImpl implements EmployeeSalaryDAO {

    List<EmployeeSalary> empSalaryList = new ArrayList<>();

    @Override
    public List<EmployeeSalary> getAll() {
        return empSalaryList;
    }

    @Override
    public boolean insert(EmployeeSalary empSalary) {
        return empSalaryList.add(empSalary);
    }

    @Override
    public List<EmployeeSalary> getByEmployeeCode(String code) {
        List<EmployeeSalary> salaries= new ArrayList<>();
        for(EmployeeSalary e:empSalaryList){
            if(e.getEmployee().getCode().equalsIgnoreCase(code)){
                salaries.add(e);
                
            }
        }
        return salaries;        
    }

}
