package br.ufes.inf.nemo.smartcast.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Tag extends PersistentObjectSupport implements Tageable{
	
	private String name;
	
	
	@Lob @ElementCollection
	private List<String> value;
	
	@OneToMany(mappedBy = "rootTag", cascade = CascadeType.ALL)
	private List<Tag> tags;
	
	@ManyToOne
	private Tag rootTag;
	
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
	
	public Tag getRootTag() {
		return rootTag;
	}

	public void setRootTag(Tag rootTag) {
		this.rootTag = rootTag;
	}

	@Override
	public List<Tag> getTags() {
		return tags;
	}

	@Override
	public void setTags(List<Tag> tags) {
		for (Tag tag : tags) {
			tag.setRootTag(this);
		}
		this.tags = tags;
	}

	@Override
	public void putTag(Tag tag){
		tag.setRootTag(this);
		this.tags.add(tag);
	}
	
	@Override
	public void putTag(String name, String tagValue){
		Tag tag = new Tag();
		tag.setValue(new ArrayList<>());
		tag.addValue(tagValue);
		tag.setName(name);
		tag.setRootTag(this);
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
		t.setRootTag(this);
		for (String string : values) {
			t.addValue(string);
			this.tags.add(t);
		}
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
