package br.ufes.inf.nemo.smartcast.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Podcast extends PersistentObjectSupport{
	
	@OneToMany(mappedBy = "podcast",cascade = CascadeType.ALL)
	private List<Episode> episodes;
	
	@ElementCollection
	@CollectionTable(name="Podcast_Tag")
	@MapKeyColumn(name="Key")
	private Map<String, String> tags;
	
	private String url;

	public List<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}

	public Map<String, String> getTags() {
		return tags;
	}

	public void setTags(Map<String, String> tags) {
		this.tags = tags;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void addTag(String value, String name){
		this.tags.put(name, value);
	}
	
	public String getTag(String name){
		return this.tags.get(name);
	}
}
