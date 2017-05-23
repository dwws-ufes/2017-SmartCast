package br.ufes.inf.nemo.smartcast.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-05-23T02:04:48.854-0300")
@StaticMetamodel(Listener.class)
public class Listener_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Listener, String> email;
	public static volatile SingularAttribute<Listener, String> password;
	public static volatile SingularAttribute<Listener, String> userName;
	public static volatile SetAttribute<Listener, Podcast> podcasts;
}
