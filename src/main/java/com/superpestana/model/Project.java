package com.superpestana.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Project implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3358660292979384634L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	String title;
	
	@ManyToMany(mappedBy = "projects")	
	Set<Employee> employees;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	public Project() {
		super();
	}

	public String getTitle() {
		
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Project(String title, Set<Employee> employees) {
		super();
		this.title = title;
		this.employees = employees;
	}

	public Project(String title) {
		super();
		this.title = title;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", title=" + title + ", employees=" + employees + "]";
	}
	
}
