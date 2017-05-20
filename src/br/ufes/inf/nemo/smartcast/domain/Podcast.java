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
public class Podcast extends PersistentObjectSupport implements Tageable{
	
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

	@Override
	public Map<String, Tag> getTags() {
		return tags;
	}

	@Override
	public void setTags(Map<String, Tag> tags) {
		this.tags = tags;
	}

	@Override
	public void putTag(String name, String value){
		Tag tag = new Tag();
		tag.addValue(value);
		tag.setName(name);
		this.tags.put(name, tag);
	}
	
	@Override
	public void putTag(String name, Tag tag){
		tag.setName(name);
		this.tags.put(name, tag);
	}
	
	@Override
	public Tag getTag(String name){
		return this.tags.get(name);
	}
	
	@Override
	public void addAllTag(String name, List<String> values) {
		this.tags.get(name).getValue().addAll(values);
	};
}
