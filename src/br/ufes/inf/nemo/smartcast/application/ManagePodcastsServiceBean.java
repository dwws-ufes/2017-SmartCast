package br.ufes.inf.nemo.smartcast.application;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.smartcast.domain.Podcast;
import br.ufes.inf.nemo.smartcast.persistence.PodcastDAO;

@Stateless
@PermitAll
public class ManagePodcastsServiceBean extends CrudServiceBean<Podcast> implements ManagePodcastsService {

	@EJB
	private PodcastDAO podcastDao;

	@Override
	public BaseDAO<Podcast> getDAO() {
		return podcastDao;
	}
}
