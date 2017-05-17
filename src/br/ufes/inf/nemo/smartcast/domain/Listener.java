package br.ufes.inf.nemo.smartcast.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Listener extends PersistentObjectSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6385113507627292453L;

	private String email;
	
	private String password;
	
	private String userName;
	
	@ManyToMany
	private Set<Podcast> podcasts;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Podcast> getPodcasts() {
		return podcasts;
	}

	public void setPodcasts(Set<Podcast> podcasts) {
		this.podcasts = podcasts;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
