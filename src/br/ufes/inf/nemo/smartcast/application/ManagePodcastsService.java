package br.ufes.inf.nemo.smartcast.application;
import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.smartcast.domain.Podcast;

@Local
public interface ManagePodcastsService extends CrudService<Podcast>{

}
