package com.odesk.model;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * A model object for Groups.
 * @author Paulo Franca
 */
@Entity
@Table(name = "GROUPS")
@JsonInclude(Include.NON_NULL)
public class Group {
	public static final int MAX_LENGTH_NAME = 20;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "GROUP_NAME", length = MAX_LENGTH_NAME)
	private String name;
	
	@Column(name = "ACTIVE")
	private boolean active;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CLIENT_GROUP", 
		joinColumns = { @JoinColumn(name = "GROUP_ID", nullable = false, updatable = true) },
		inverseJoinColumns = { @JoinColumn(name = "CLIENT_ID", nullable = false, updatable = true) })
	private Set<Client> clients;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "FORM_GROUP", 
		joinColumns = { @JoinColumn(name = "GROUP_ID", nullable = false, updatable = true) },
		inverseJoinColumns = { @JoinColumn(name = "FORM_ID", nullable = false, updatable = true) })
	private Set<Form> forms;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "groups")
	private Set<User> users = new HashSet<User>(0);
	
	@Version
	private long version;

	public Group() {}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isActive() {
		return active;
	}

	public Set<Client> getClients() {
		return clients;
	}
	
	public Set<Form> getForms() {
		return forms;
	}

	public Set<User> getUsers() {
		return users;
	}

	public long getVersion() {
		return version;
	}
	
	public static Builder getBuilder(String name) {
		return new Builder(name);
	}
	
	public static class Builder {
		private Group built;

		public Builder(String name) {
			built.name = name;
		}
		
		public Builder client(Client client) {
			built.clients.add(client);
			return this;
		}
		
		public Builder clients(Set<Client> clients) {
			built.clients.addAll(clients);
			return this;
		}
		
		public Builder form(Form form) {
			built.forms.add(form);
			return this;
		}
		
		public Builder forms(Set<Form> forms) {
			built.forms.addAll(forms);
			return this;
		}
		
		public Group build() {
			return built;
		}
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