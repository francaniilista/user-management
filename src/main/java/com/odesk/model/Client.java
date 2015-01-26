package com.odesk.model;

import java.io.IOException;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * A model object for Clients.
 * @author Paulo Franca
 *
 */
@Entity
@Table(name = "CLIENT")
public class Client implements Jsonable {
	public static final int MAX_LENGTH_NAME = 20;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "CLIENT_NAME")
	private String name;

	@Column(name = "ACTIVE")
	private boolean active;
	
	@OneToMany(mappedBy = "id", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private Set<Form> forms;
	
	@Version
	private long version;

	public Client() {}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isActive() {
		return active;
	}

	public Set<Form> getForms() {
		return forms;
	}

	public long getVersion() {
		return version;
	}
	
	public static Builder getBuilder(String name) {
		return new Builder(name);
	}
	
	public static class Builder {
		private Client built;

		public Builder(String name) {
			built.name = name;
		}
		
		public Builder form(Form form) {
			built.forms.add(form);
			return this;
		}
		
		public Builder forms(Set<Form> forms) {
			built.forms.addAll(forms);
			return this;
		}
		
		public Client build() {
			return built;
		}
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String toJSON() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
		mapper.enable(Feature.INDENT_OUTPUT);

		return mapper.writeValueAsString(this);
	}
}