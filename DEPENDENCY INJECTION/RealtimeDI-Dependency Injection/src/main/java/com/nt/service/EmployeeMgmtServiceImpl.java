package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dao.IEmployeeDAO;
import com.nt.model.Employee;

@Service("empService")
public class EmployeeMgmtServiceImpl implements IEmployeeMgmtService {
	@Autowired
	private IEmployeeDAO  empDAO;

	@Override
	public List<Employee> fetchEmployeesByDesg(String desg1, String desg2, String desg3) throws Exception {
		//convert given  Desgs into  uppercase letters (b.logic)
		desg1=desg1.toUpperCase();
		desg2=desg2.toUpperCase();
		desg3=desg3.toUpperCase();
		//use  DAO
		List<Employee>  list=empDAO.showEmployeesByDesgs(desg1, desg2, desg3);
		// calculate grosss salary and netsalary
		list.forEach(emp->{
			emp.setGrossSalary(emp.getSalary()+(emp.getSalary()*0.5));
			emp.setNetSalary(emp.getGrossSalary()-(emp.getGrossSalary()*0.2));
		});
		
		return list;
	}

	@Override
	public String registerEmployee(Employee emp) throws Exception {
		//use DAO
		int count=empDAO.insert(emp);
		return count==0?"Employee Registration Failed":"Employee Registered Successfully";
	}

}
