package com.supra.sso.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role{

	private Long id;
    private String authority;
    private Set<User> users;

    
    public Role() {
		super();
	}

	public Role(Long id, String authority, Set<User> users) {
		super();
		this.id = id;
		this.authority = authority;
		this.users = users;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="name")
    public String getAuthority() {
    	return authority;
    }
    
    public void setAuthority(String authority) {
    	this.authority = authority;
    }


    @ManyToMany(mappedBy = "roles", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }



	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + authority + ", users=" + users + "]";
	}

    
    
}