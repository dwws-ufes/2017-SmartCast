package br.ufes.inf.nemo.smartcast.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.smartcast.domain.Podcast;
import br.ufes.inf.nemo.smartcast.domain.Tag;

@Local
public interface PodcastDAO extends BaseDAO<Podcast>{
	
	public List<Podcast> retrieveByTag(String value);
	
	public List<Podcast> retrieveByTag(String name, String value);
	
	public List<Podcast> retrieveByTag(Tag tag);
	
	public int countByURL(String url);
	
	public Podcast retrieveByURL(String url);
}
