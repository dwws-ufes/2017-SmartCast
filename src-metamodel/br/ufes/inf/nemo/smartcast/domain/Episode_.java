package br.ufes.inf.nemo.smartcast.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-05-16T16:26:49.522-0300")
@StaticMetamodel(Episode.class)
public class Episode_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Episode, Podcast> podcast;
	public static volatile MapAttribute<Episode, String, String> tags;
}
