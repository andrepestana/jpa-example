package com.superpestana.repository;

import java.util.HashSet;
import java.util.Set;

import org.h2.tools.Server;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.superpestana.model.Employee;
import com.superpestana.model.Project;

public class IntegrationTest {

	Server server = null;
	
	@Before
	public void before() {
		startH2();
	}
	
	private static void startH2(){
	    Server tcpServer = null;
	    Server webServer = null;
	    try {
	        tcpServer = Server.createTcpServer("-tcpAllowOthers").start();
	        System.out.println("TCP Server Port: " + tcpServer.getPort());
	        Class.forName("org.h2.Driver");
	        
	        webServer = Server.createWebServer().start();
	        System.out.println("Web Server (H2Console) Port: " + webServer.getPort());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@After
	public void after() {
		if(server != null) {
			server.shutdown();
		}
	}
	
	@Test
	public void firstTest() {
        Set<Project> projects1 = new HashSet<>();
        projects1.add(new Project("Project 1"));
        projects1.add(new Project("Project 2"));
        projects1.add(new Project("Project 3"));
        
        Employee employee1 = new Employee("John", projects1);
        
        Set<Project> projects2 = new HashSet<>();
        projects2.add(new Project("Project 4"));
        Employee employee2 = new Employee("Mary", projects2);
        
        EmployeeRepository empRepo = new EmployeeRepository();
        empRepo.save(employee1);
        empRepo.save(employee2);
        
        Employee employee1FromDb = empRepo.findById(1l);
        Assert.assertEquals("John", employee1FromDb.getName());
        Assert.assertEquals(3, employee1FromDb.getProjects().size());
        
        Employee employee2FromDb = empRepo.findById(2l);
        Assert.assertEquals("Mary", employee2FromDb.getName());
        Assert.assertEquals(1, employee2FromDb.getProjects().size());
	}
}
