package br.ufes.inf.nemo.smartcast.domain;

import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Tag extends PersistentObjectSupport implements Tageable{
	
	private String name;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public void putTag(String name, Tag tag){
		tag.setName(name);
		this.tags.put(name, tag);
	}
	
	@Override
	public void putTag(String name, String tagValue){
		Tag tag = new Tag();
		tag.addValue(tagValue);
		tag.setName(name);
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

	@Override
	public Tag getTag(String name) {
		return this.tags.get(name);
	}
	
	@Override
	public void addAllTag(String name, List<String> values) {
		this.tags.get(name).getValue().addAll(values);
	};
}
