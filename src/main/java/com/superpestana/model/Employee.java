package com.superpestana.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7619005557863294620L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	String name;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
	        name = "Employee_Project", 
	        joinColumns = { @JoinColumn(name = "employee_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "project_id") }
	    )
	Set<Project> projects;

	public Employee(String name, Set<Project> projects) {
		super();
		this.name = name;
		this.projects = projects;
	}
	

	public Employee() {
		super();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", projects=" + projects + "]";
	}

	
}
