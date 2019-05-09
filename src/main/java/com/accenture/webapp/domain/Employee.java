package com.accenture.webapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

	@Column(name = "emp_ei", length = 50, nullable = true)
    private String empEID;

    @Column(name = "emp_capability", length = 50, nullable = true)
    private String empCapability;

    @Column(name = "emp_ig", length = 50, nullable = true)
    private String empIG;
    
    @Column(name = "emp_level", length = 50, nullable = true)
    private int empLevel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getEmpEID() {
		return empEID;
	}

	public void setEmpEID(String empEID) {
		this.empEID = empEID;
	}

	public String getEmpCapability() {
		return empCapability;
	}

	public void setEmpCapability(String empCapability) {
		this.empCapability = empCapability;
	}

	public String getEmpIG() {
		return empIG;
	}

	public void setEmpIG(String empIG) {
		this.empIG = empIG;
	}

	public int getEmpLevel() {
		return empLevel;
	}

	public void setEmpLevel(int empLevel) {
		this.empLevel = empLevel;
	}


}
