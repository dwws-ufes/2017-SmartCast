package br.ufes.inf.nemo.smartcast.domain;

import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Tag extends PersistentObjectSupport {
	
	@ElementCollection
	private List<String> value;
	
	@ElementCollection
	@CollectionTable(name="tag_tag")
	@MapKeyColumn(name="tagname")
	private Map<String, Tag> tags;
	
	@ElementCollection
	@CollectionTable(name="tag_attributes")
	@MapKeyColumn(name="attributenamename")
	private Map<String, String> attributes;

	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		this.value = value;
	}
	
	public void addValue(String value){
		this.value.add(value);
	}

	public Map<String, Tag> getTags() {
		return tags;
	}

	public void setTags(Map<String, Tag> tags) {
		this.tags = tags;
	}

	public void putTag(String name, Tag t){
		this.tags.put(name, t);
	}
	
	public void putTag(String name, String tagValue){
		Tag tag = new Tag();
		tag.addValue(tagValue);
		this.tags.put(name, tag);
	}
	
	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
	public void putAttribute(String name, String attribute){
		this.attributes.put(name, attribute);
	}
}
