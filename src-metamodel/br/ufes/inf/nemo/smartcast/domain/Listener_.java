package br.ufes.inf.nemo.smartcast.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="Dali", date="2017-07-28T17:04:15.272-0300")
=======
@Generated(value="Dali", date="2017-07-13T15:17:39.567-0300")
>>>>>>> refs/remotes/origin/master
@StaticMetamodel(Listener.class)
public class Listener_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Listener, String> email;
	public static volatile SingularAttribute<Listener, String> password;
	public static volatile SingularAttribute<Listener, String> userName;
	public static volatile SetAttribute<Listener, Podcast> podcasts;
}
