package br.ufes.inf.nemo.smartcast.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-05-21T18:08:33.535-0300")
@StaticMetamodel(Tag.class)
public class Tag_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Tag, String> name;
	public static volatile ListAttribute<Tag, String> value;
	public static volatile MapAttribute<Tag, String, Tag> tags;
	public static volatile MapAttribute<Tag, String, String> attributes;
}
