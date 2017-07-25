package br.ufes.inf.nemo.smartcast.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-25T16:05:55.609-0300")
@StaticMetamodel(Podcast.class)
public class Podcast_ extends PersistentObjectSupport_ {
	public static volatile ListAttribute<Podcast, Episode> episodes;
	public static volatile SingularAttribute<Podcast, String> url;
	public static volatile ListAttribute<Podcast, Tag> tags;
}
