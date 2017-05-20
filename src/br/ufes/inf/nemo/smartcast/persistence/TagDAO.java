package br.ufes.inf.nemo.smartcast.persistence;

import java.util.List;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.smartcast.domain.Tag;

public interface TagDAO extends BaseDAO<Tag>{
	
	public List<Tag> retrieveByName(String name);
	
	public List<Tag> retrieveByValue(String value);

}
