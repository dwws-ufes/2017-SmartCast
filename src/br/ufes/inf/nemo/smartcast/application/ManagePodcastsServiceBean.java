package br.ufes.inf.nemo.smartcast.application;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.smartcast.domain.Podcast;
import br.ufes.inf.nemo.smartcast.persistence.PodcastDAO;

@Stateless
@PermitAll
public class ManagePodcastsServiceBean implements ManagePodcastsService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManagePodcastsServiceBean.class.getCanonicalName());

	@EJB
	private PodcastDAO podcastDao;

	public List<Podcast> getSome(){
		int size = (int) podcastDao.retrieveCount();
		int interval[] = {0,Integer.min(size, 6)};
		
		return podcastDao.retrieveSome(interval);
	}
	
	
	
	@Override
	public List<Podcast> search(String strg) {
		List<Podcast> result = new ArrayList<>();
		try {
			if (podcastDao.countByURL(strg) == 0) {
				ParsePodcastFeed parser = new ParsePodcastFeed(strg);
				Podcast podcast = parser.readFeed();
				podcastDao.save(podcast);
			}
			result.add(podcastDao.retrieveByURL(strg));
		} catch (MalformedURLException e) {
			result.addAll(podcastDao.retrieveByTag("title", strg));
		}
		return result;
	}



	@Override
	public List<Podcast> podcasts() {
		List<Podcast> result = podcastDao.retrieveAll();
		System.out.println("SIZE: " + result.size());
		return result;
	}

}