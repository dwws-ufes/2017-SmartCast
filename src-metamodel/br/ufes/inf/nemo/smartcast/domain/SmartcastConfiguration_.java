package br.ufes.inf.nemo.smartcast.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-25T11:09:55.943-0300")
@StaticMetamodel(SmartcastConfiguration.class)
public class SmartcastConfiguration_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<SmartcastConfiguration, Date> creationDate;
	public static volatile SingularAttribute<SmartcastConfiguration, String> institutionAcronym;
}
