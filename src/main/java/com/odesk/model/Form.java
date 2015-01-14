package com.odesk.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * A model object for forms.
 * @author paulo.franca
 *
 */
@Entity
@Table(name = "FORM")
public class Form {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(targetEntity = Class.class, fetch = FetchType.LAZY)
	private Client clients;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "FORM_GROUP", 
		joinColumns = { @JoinColumn(columnDefinition = "GROUP_ID", nullable = false, updatable = true) },
		inverseJoinColumns = { @JoinColumn(columnDefinition = "FORM_ID", nullable = false, updatable = true) })
	private Set<Group> groups;

	public Form() {}
	
	public long getId() {
		return id;
	}

	public Client getClients() {
		return clients;
	}

	public Set<Group> getGroups() {
		return groups;
	}
}