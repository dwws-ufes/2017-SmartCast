package br.ufes.inf.nemo.smartcast.domain;

import java.util.List;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Episode extends PersistentObjectSupport implements Tageable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8044979677176622862L;

	@ManyToOne
	private Podcast podcast;
	
	@ElementCollection
	@CollectionTable(name="episode_tag")
	@MapKeyColumn(name="tagname")
	private Map<String, Tag> tags;

	public Podcast getPodcast() {
		return podcast;
	}

	protected void setPodcast(Podcast podcast) {
		this.podcast = podcast;
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
