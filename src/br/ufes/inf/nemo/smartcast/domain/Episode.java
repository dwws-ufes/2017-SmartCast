package br.ufes.inf.nemo.smartcast.domain;

import java.util.ArrayList;
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
	
	@OneToMany
	private List<Tag> tags;

	public Podcast getPodcast() {
		return podcast;
	}

	protected void setPodcast(Podcast podcast) {
		this.podcast = podcast;
	}

	@Override
	public List<Tag> getTags() {
		return tags;
	}

	@Override
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public void putTag(Tag tag){
		this.tags.add(tag);
	}
	
	@Override
	public void putTag(String name, String tagValue){
		Tag tag = new Tag();
		tag.setValue(new ArrayList<>());
		tag.addValue(tagValue);
		tag.setName(name);
		this.tags.add(tag);
	}
	
	@Override
	public Tag getTag(String name){
		Tag t = null;
		for (Tag tag : tags) {
			if(tag.getName().equals(name)){
				t = tag;
				break;
			}
		}
		return t;
	}
	
	@Override
	public void addAllTag(String name, List<String> values) {
		Tag t = new Tag();
		t.setName(name);
		t.setValue(new ArrayList<>());
		for (String string : values) {
			t.addValue(string);
			this.tags.add(t);
		}
	}
}
