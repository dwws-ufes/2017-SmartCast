package br.ufes.inf.nemo.smartcast.persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.smartcast.domain.Tag;
import br.ufes.inf.nemo.smartcast.domain.Tag_;

public class TagJPADAO extends BaseJPADAO<Tag> implements TagDAO {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(TagJPADAO.class.getCanonicalName());

	/**
	 * The application's persistent context provided by the application server.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<Tag> retrieveByName(String name) {
		logger.log(Level.FINE, "Retrieving the tags wich name is \"{0}\"...", new Object[] { name });

		// Constructs the query over the Tag class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tag> cq = cb.createQuery(Tag.class);
		Root<Tag> root = cq.from(Tag.class);

		// Filters the query with the name.
		cq.where(cb.equal(root.get(Tag_.name), name));
		List<Tag> result = entityManager.createQuery(cq).getResultList();
		logger.log(Level.INFO, "Retrieve the tags wich name is \"{0}\" returned {1} results.",
				new Object[] { name, result.size() });
		return result;
	}

	@Override
	public List<Tag> retrieveByValue(String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
