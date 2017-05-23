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
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.smartcast.domain.SmartcastConfiguration;
import br.ufes.inf.nemo.smartcast.domain.SmartcastConfiguration_;

/**
 * Stateless session bean implementing a DAO for objects of the MarvinConfiguration domain class using JPA2.
 * 
 * Using a mini CRUD framework for EJB3, basic DAO operation implementations are inherited from the superclass, whereas
 * operations that are specific to the managed domain class (if any is defined in the implementing DAO interface) have
 * to be implemented in this class.
 * 
 * @author Vitor E. Silva Souza (vitorsouza@gmail.com)
 * @see br.org.feees.sigme.core.domain.Attendance
 * @see br.org.feees.sigme.core.persistence.AttendanceDAO
 */
@Stateless
public class SmartcastConfigurationJPADAO extends BaseJPADAO<SmartcastConfiguration> implements SmartcastConfigurationDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(SmartcastConfigurationJPADAO.class.getCanonicalName());

	/** The application's persistent context provided by the application server. */
	@PersistenceContext
	private EntityManager entityManager;

	/** @see br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO#getEntityManager() */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/** @see br.SmartcastConfigurationDAO.feees.sigme.core.persistence.MarvinConfigurationDAO#retrieveCurrentConfiguration() */
	@Override
	public SmartcastConfiguration retrieveCurrentConfiguration() throws PersistentObjectNotFoundException {
		logger.log(Level.FINE, "Retrieving current (latest) Marvin configuration...");

		// Constructs the query over the MarvinConfiguration class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<SmartcastConfiguration> cq = cb.createQuery(SmartcastConfiguration.class);
		Root<SmartcastConfiguration> root = cq.from(SmartcastConfiguration.class);

		// Orders the query descending by date.
		//cq.orderBy(cb.desc(root.get(SmartcastConfiguration.creationDate)));

		// Retrieves and returns the latest configuration (first entity returned). If the query returns an empty list,
		// throws an exception.
		List<SmartcastConfiguration> result = entityManager.createQuery(cq).getResultList();
		try {
			SmartcastConfiguration cfg = result.get(0);
			logger.log(Level.INFO, "Retrieve current configuration returned a MarvinConfiguration with institution \"{0}\" and creation date \"{1}\"", new Object[] { cfg.getInstitutionAcronym(), cfg.getCreationDate() });
			return cfg;
		}
		catch (IndexOutOfBoundsException e) {
			throw new PersistentObjectNotFoundException(e, getDomainClass());
		}
	}
}