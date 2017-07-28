package br.ufes.inf.nemo.smartcast.domain;

import java.util.ArrayList;
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
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Tag> tags;
	
	private String url;
	
	public String getImage(){
		return this.getTag("image").getTag("url").getValue().get(0);
	}
	
	public String getTitle(){
		return this.getTag("title").getValue().get(0);
	}
	
	public String getLink(){
		return this.getTag("image").getTag("link").getValue().get(0);
	}
	
	public List<Episode> getEpisodes() {
		return episodes;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}
	
	public void addEpisode(Episode episode){
		episode.setPodcast(this);
		this.episodes.add(episode);
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
			if(!string.isEmpty()){
				System.out.println(string);
				t.addValue(string);
				this.tags.add(t);
			}
		}
	}

	
}
