package br.ufes.inf.nemo.smartcast.application;
import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.smartcast.domain.Podcast;

@Local
public interface ManagePodcastsService{
	public List<Podcast> search(String strg);
	public List<Podcast> getSome();
}