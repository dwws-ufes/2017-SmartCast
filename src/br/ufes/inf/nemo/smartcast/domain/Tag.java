package br.ufes.inf.nemo.smartcast.domain;

import java.util.Set;

import javax.persistence.Entity;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Tag /*<T>*/ extends PersistentObjectSupport{ //TODO: Check how to do template
	
	private String name;
	
//	private T value;
	
//	private Set<Tag> tags; //TODO: Check how to do self reference
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
//	public T getValue() {
//		return value;
//	}
//	
//	public void setValue(T value) {
//		this.value = value;
//	}
	
//	public Set<Tag> getTags() {
//		return tags;
//	}
//	
//	public void setTags(Set<Tag> tags) {
//		this.tags = tags;
//	}
	
	
}
