package br.ufes.inf.nemo.smartcast.domain;

import java.util.List;
import java.util.Map;

public interface Tageable {
	
	public Map<String, Tag> getTags();

	public void setTags(Map<String, Tag> tags);
	
	public void putTag(String name, String value);
	
	public void putTag(String name, Tag t);
	
	public Tag getTag(String name);
	
	public void addAllTag(String name, List<String> values);
	
}
