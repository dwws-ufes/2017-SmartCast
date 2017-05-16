package br.ufes.inf.nemo.smartcast.domain;

import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Episode extends PersistentObjectSupport {

	@ManyToOne
	private Podcast podcast;
	
	@ElementCollection
	@CollectionTable(name="Episode_Tag")
	@MapKeyColumn(name="Key")
	private Map<String, String> tags;

	public Podcast getPodcast() {
		return podcast;
	}

	public void setPodcast(Podcast podcast) {
		this.podcast = podcast;
	}

	public Map<String, String> getTags() {
		return tags;
	}

	public void setTags(Map<String, String> tags) {
		this.tags = tags;
	}
	
	public void addTag(String value, String name){
		this.tags.put(name, value);
	}
	
	public String getTag(String name){
		return this.tags.get(name);
	}
}
