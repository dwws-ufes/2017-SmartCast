package br.ufes.inf.nemo.smartcast.persistence;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.smartcast.domain.Listener;
import br.ufes.inf.nemo.smartcast.domain.Listener_;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Stateless
public class ListenerJPADAO extends BaseJPADAO<Listener> implements ListenerDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ListenerJPADAO.class.getCanonicalName());

	/** The application's persistent context provided by the application server. */
	@PersistenceContext
	private EntityManager entityManager;

	/** @see br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO#getEntityManager() */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/** @see br.ufes.inf.nemo.ListenerDAO.core.persistence.AcademicDAO#retrieveByEmail(java.lang.String) */
	@Override
	public Listener retrieveByEmail(String email) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {
		logger.log(Level.FINE, "Retrieving the Listener whose e-mail is \"{0}\"...", email);

		// Constructs the query over the Academic class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery< Listener> cq = cb.createQuery( Listener.class);
		Root< Listener> root = cq.from( Listener.class);

		// Filters the query with the email.
		cq.where(cb.equal(root.get( Listener_.email), email));
		 Listener result = executeSingleResultQuery(cq, email);
		logger.log(Level.INFO, "Retrieve Listener by the email \"{0}\" returned \"{1}\"", new Object[] { email, result });
		return result;
	}
}
