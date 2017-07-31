package br.ufes.inf.nemo.smartcast.application;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.ufes.inf.nemo.smartcast.domain.Podcast;
import br.ufes.inf.nemo.smartcast.persistence.PodcastDAO;
@ApplicationScoped

@ManagedBean(name = "podcastServiceResult")

public class PodcastServiceResult {
	@EJB
	private PodcastDAO podcastDao;
	public Podcast podcasts() {
		List<Podcast> result = podcastDao.retrieveAll();
		Podcast lastPodcast = new Podcast();
		lastPodcast  = result.get(result.size() - 1);
		System.out.println("SIZE: " + result.size());
		return lastPodcast;
	
	}

}
