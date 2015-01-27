package com.odesk.model.dto;

import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.odesk.model.Group;
import com.odesk.model.User;

public class UserDTO {
	private long id;
	
	@NotEmpty
	@Length(max = User.MAX_LENGTH_USERNAME)
	private String username;
	
	@NotEmpty
	@Length(max = User.MAX_LENGTH_PASSWORD)
	private String password;
	
	@Email
	@Length(max = User.MAX_LENGTH_EMAIL_ADDRESS)
	private String email;
	
	private boolean active;
	
	private Set<Group> groups;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}