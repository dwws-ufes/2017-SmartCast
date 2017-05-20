package br.ufes.inf.nemo.smartcast.persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.smartcast.domain.Podcast;
import br.ufes.inf.nemo.smartcast.domain.Podcast_;
import br.ufes.inf.nemo.smartcast.domain.Tag;
import br.ufes.inf.nemo.smartcast.domain.Tag_;

@Stateless
public class PodcastJPADAO extends BaseJPADAO<Podcast> implements PodcastDAO {
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	/** The logger. */
	private static final Logger logger = Logger.getLogger(TagJPADAO.class.getCanonicalName());
	
	/** The application's persistent context provided by the application server. */
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<Podcast> retrieveByTag(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Podcast> retrieveByTag(String name, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Podcast> retrieveByTag(Tag tag) {
		
		logger.log(Level.FINE, "Retrieving the podcasts wich have tag \"{0}\"...", new Object[] { tag.getName() });

		// Constructs the query over the Tag class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Podcast> cq = cb.createQuery(Podcast.class);
		Root<Podcast> root = cq.from(Podcast.class);

		// Filters the query with the name.
		cq.where(cb.isMember(tag, root.get(Podcast_.tags)));
		List<Podcast> result = entityManager.createQuery(cq).getResultList();
		logger.log(Level.INFO, "Retrieve the podcasts wich have tag \"{0}\" returned {1} results.",
				new Object[] { tag.getName(), result.size() });
		return result;
	}

}
