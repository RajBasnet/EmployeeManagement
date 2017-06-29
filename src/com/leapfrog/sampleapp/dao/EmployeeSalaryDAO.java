/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.sampleapp.dao;

import com.leapfrog.sampleapp.entity.EmployeeSalary;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface EmployeeSalaryDAO {

    List<EmployeeSalary> getAll();

    boolean insert(EmployeeSalary empSalary);

    List<EmployeeSalary> getByEmployeeCode(String code);

}
