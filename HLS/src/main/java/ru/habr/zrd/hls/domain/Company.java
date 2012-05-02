package ru.habr.zrd.hls.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "COMPANY")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Company {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "S_NAME")
	private String name;
	
	@OneToMany
	@JoinColumn(name = "ID_COMPANY")	
	@XmlElementWrapper // Обернем коллекцию дополнительным тегом, чтобы не получить "плоскую" выдачу.
	@XmlElement(name = "supplier")
	private Set<Supplier> suppliers;
	
	@OneToMany
	@JoinColumn(name = "ID_COMPANY")
	@XmlElementWrapper // Обернем коллекцию дополнительным тегом, чтобы не получить "плоскую" выдачу.
	@XmlElement(name = "customer")
	private Set<Customer> customers;

	// --- GETTERS / SETTERS ---
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Supplier> getSuppliers() {
		return suppliers;
	}
	public void setSuppliers(Set<Supplier> suppliers) {
		this.suppliers = suppliers;
	}
	public Set<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
}