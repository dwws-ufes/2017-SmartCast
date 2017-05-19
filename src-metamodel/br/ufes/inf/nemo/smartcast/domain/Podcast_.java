package br.ufes.inf.nemo.smartcast.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-05-19T09:58:34.913-0300")
@StaticMetamodel(Podcast.class)
public class Podcast_ extends PersistentObjectSupport_ {
	public static volatile ListAttribute<Podcast, Episode> episodes;
	public static volatile MapAttribute<Podcast, String, Tag> tags;
}
