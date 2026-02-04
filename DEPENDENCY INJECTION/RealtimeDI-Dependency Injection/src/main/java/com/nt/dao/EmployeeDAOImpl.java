package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nt.model.Employee;

@Repository("empDAO")
public class EmployeeDAOImpl implements IEmployeeDAO {
	private  static final String  GET_EMPS_BY_DESGS="SELECT EMPNO,ENAME,JOB,SAL FROM EMP  WHERE JOB IN(?,?,?) ORDER BY JOB";
	private  static final String  INSERT_EMPLOYEE="INSERT INTO EMP(EMPNO,ENAME,JOB,SAL)  VALUES(EMP_SEQ13.NEXTVAL,?,?,?) ";

	@Autowired
	private  DataSource  ds;

	@Override
	public List<Employee> showEmployeesByDesgs(String desg1, String desg2, String desg3) throws Exception {
		System.out.println("EmployeeDAOImpl.showEmployeesByDesgs() ::"+ds.getClass());
		List<Employee>  list=null;
		//get Pooled jdbc con object
		try(Connection con=ds.getConnection();
				 //statement object
				PreparedStatement  ps=con.prepareStatement(GET_EMPS_BY_DESGS);){
			  //set values to query params
			ps.setString(1, desg1); ps.setString(2, desg2); ps.setString(3, desg3);
			//execute  the Query
			try(ResultSet rs=ps.executeQuery()){
				//convert ResultSEt object recrords to List<Employee> class object
				list=new ArrayList<Employee>();
				while(rs.next()) {
					Employee emp=new Employee();
					emp.setEno(rs.getInt(1));
					emp.setEname(rs.getString(2));
					emp.setDesg(rs.getString(3));
					emp.setSalary(rs.getDouble(4));
					list.add(emp);
				}//while
			}//try2
			
		}//try1
		catch(SQLException se) {
			throw se;  //Exception rethrowing
		}
		catch(Exception e) {
			throw e;  //Exception rethrowing
		}
		return list;
	}//method

	@Override
	public int insert(Employee emp) throws Exception {
		System.out.println("EmployeeDAOImpl.insert()");
		try(Connection con=ds.getConnection();
				PreparedStatement ps=con.prepareStatement(INSERT_EMPLOYEE);) {
			     //set the values for query params
			    ps.setString(1, emp.getEname());
			    ps.setString(2,emp.getDesg());
			    ps.setDouble(3, emp.getSalary());
			    //execute the Query
			    int count=ps.executeUpdate();
			   return count;
		}
		catch(SQLException se) {
		   throw se;
		}
	    catch(Exception e) {
	    	throw e;
	    }
	}
}//class
