package br.ufes.inf.nemo.smartcast.application;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.ufes.inf.nemo.smartcast.domain.Podcast;
import br.ufes.inf.nemo.smartcast.persistence.PodcastDAO;
@ApplicationScoped

@ManagedBean(name = "podcastService")

public class PodcastService {
	@EJB
	private PodcastDAO podcastDao;
	public List<Podcast> podcasts() {
		List<Podcast> result = podcastDao.retrieveAll();
		System.out.println("SIZE: " + result.size());
		return result;
	
	}

}
