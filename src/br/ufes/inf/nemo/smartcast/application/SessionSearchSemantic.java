package br.ufes.inf.nemo.smartcast.application;

import java.io.Serializable;

import javax.ejb.Local;

import org.apache.jena.query.ResultSet;

import br.ufes.inf.nemo.smartcast.domain.Podcast;
//import br.ufes.inf.nemo.smartcast.persistence.PodcastDAO;

@Local
public interface SessionSearchSemantic extends Serializable{
	//private PodcastDAO podcastDAO;

	Podcast getCurrentPodcast();
	void savePodcastSearch(ResultSet results);
	
}
