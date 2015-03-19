package com.odesk.model;

import java.io.IOException;
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

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * A model object for forms.
 * @author paulo.franca
 *
 */
@Entity
@Table(name = "FORM")
@JsonInclude(Include.NON_NULL)
public class Form {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(targetEntity = Client.class, fetch = FetchType.LAZY)
	private Client clients;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "FORM_GROUP", 
		joinColumns = { @JoinColumn(name = "GROUP_ID", nullable = false, updatable = true) },
		inverseJoinColumns = { @JoinColumn(name = "FORM_ID", nullable = false, updatable = true) })
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
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String toJSON() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		return mapper.writeValueAsString(this);
	}
}