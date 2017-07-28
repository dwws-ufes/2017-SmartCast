package br.ufes.inf.nemo.smartcast.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-25T11:09:55.911-0300")
@StaticMetamodel(Episode.class)
public class Episode_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Episode, Podcast> podcast;
	public static volatile ListAttribute<Episode, Tag> tags;
}
