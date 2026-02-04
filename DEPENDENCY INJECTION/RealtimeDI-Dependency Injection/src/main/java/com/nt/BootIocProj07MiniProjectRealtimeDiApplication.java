package com.nt;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.nt.controller.EmployeeOperationsController;
import com.nt.model.Employee;

@SpringBootApplication
public class BootIocProj07MiniProjectRealtimeDiApplication {

	public static void main(String[] args) {
		   //get access to IOC container
		try(ConfigurableApplicationContext ctx=
				   SpringApplication.run(BootIocProj07MiniProjectRealtimeDiApplication.class, args);
				Scanner sc=new Scanner(System.in);
				){
		//  get Controller class obj  ref
			EmployeeOperationsController controller=
							                 ctx.getBean("empController",EmployeeOperationsController.class);
					
			System.out.println("Enter  employee name ::");
			String  name=sc.next();
			
			System.out.println("Enter  employee Desg ::");
			String  desg=sc.next();
			
			System.out.println("Enter  employee Salary ::");
			double salary=sc.nextDouble();
			
			//create Employee class object having the data
			Employee  emp=new Employee(name, desg, salary);
			//invoke the method 
			String msg=controller.saveEmployee(emp);
			System.out.println(msg);
			
			System.out.println("===================================");
			   System.out.println("Enter Desg1::");
			   String desg1=sc.next();
			   System.out.println("Enter Desg2::");
			   String desg2=sc.next();
			   System.out.println("Enter Desg3::");
			   String desg3=sc.next();
			   
			//invoke the b.method
			List<Employee> list=controller.findEmployeesByDesgs(desg1, desg2, desg3);
			list.forEach(emp1->{
				System.out.println(emp1);
			});
			
			System.out.println("====================================");
			
			
		}
		catch(Exception e) {
			System.out.println("Internal Problem  ---try again::"+e.getMessage());
			e.printStackTrace();
		}
	}//main

}//class
