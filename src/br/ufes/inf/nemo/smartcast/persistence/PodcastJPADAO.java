package br.ufes.inf.nemo.smartcast.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.smartcast.domain.Podcast;

@Stateless
public class PodcastJPADAO extends BaseJPADAO<Podcast> implements PodcastDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -241958215854984476L;
	/** The application's persistent context provided by the application server. */
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
