package br.ufes.inf.nemo.smartcast.domain;

import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Podcast extends PersistentObjectSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1845740500428456889L;

	@OneToMany(mappedBy = "podcast",cascade = CascadeType.ALL)
	private List<Episode> episodes;
	
	@ElementCollection
	@CollectionTable(name="podcast_tag")
	@MapKeyColumn(name="tagname")
	private Map<String, Tag> tags;
	
	private String url;

	public List<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}
	
	public void addEpisode(Episode episode){
		episode.setPodcast(this);
		this.episodes.add(episode);
	}

	public Map<String, Tag> getTags() {
		return tags;
	}

	public void setTags(Map<String, Tag> tags) {
		this.tags = tags;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void putTag(String name, String value){
		Tag t = new Tag();
		t.addValue(value);
		this.tags.put(name, t);
	}
	
	public void putTag(String name, Tag t){
		this.tags.put(name, t);
	}
	
	public Tag getTag(String name){
		return this.tags.get(name);
	}
}
