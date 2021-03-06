package com.odesk.model;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


/**
 * A model object for users.
 * @author Paulo Franca
 */
@Entity
@Table(name = "USERS")
@JsonInclude(Include.NON_NULL)
public class User {
	public static final int MAX_LENGTH_USERNAME = 20;
	public static final int MAX_LENGTH_PASSWORD = 10;
	public static final int MAX_LENGTH_EMAIL_ADDRESS = 100;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "USERNAME", length = MAX_LENGTH_USERNAME)
	private String username;

	@Column(name = "PASSWORD", length = MAX_LENGTH_PASSWORD)
	private String password;

	@Column(name = "EMAIL", length = MAX_LENGTH_EMAIL_ADDRESS)
	private String email;

	@Column(name = "ACTIVE")
	private boolean active;

	@Column(name = "CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar createTime;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_GROUP", 
		joinColumns = { @JoinColumn(name = "USER_ID", nullable = true, updatable = true) },
		inverseJoinColumns = { @JoinColumn(name = "GROUP_ID", nullable = true, updatable = true) })
	private Set<Group> groups = new HashSet<Group>(0);

	@Version
	private long version;

	public User() {
	}

	public static Builder getBuilder(String username, String password) {
        return new Builder(username, password);
    }

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public boolean isActive() {
		return active;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public long getVersion() {
		return version;
	}
	
	public void update(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public void updateEmail(String email) {
		this.email = email;
	}
	
	public static class Builder {
		private User built;
		
		public Builder(String username, String password) {
			built = new User();
			built.username = username;
			built.password = password;
			built.active = false;
		}
		
		public Builder email(String email) {
            built.email = email;
            return this;
        }
		
		public Builder group(Group group) {
			built.groups.add(group);
			return this;
		}
		
		public Builder groups(Set<Group> groups) {
			if (groups != null) {
				built.groups.addAll(groups);
			}
			return this;
		}
		
		public User build() {
			built.createTime = Calendar.getInstance();
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