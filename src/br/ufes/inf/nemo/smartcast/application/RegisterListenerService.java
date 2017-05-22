package br.ufes.inf.nemo.smartcast.application;

import java.io.Serializable;

import javax.ejb.Local;

import br.ufes.inf.nemo.smartcast.domain.Listener;
//import br.ufes.inf.nemo.smartcast.domain.MarvinConfiguration;
import br.ufes.inf.nemo.smartcast.exceptions.SystemInstallFailedException;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Local
public interface RegisterListenerService extends Serializable {
	/**
	 * TODO: document this method.
	 * 
	 * @param config
	 * @param admin
	 * @throws SystemInstallFailedException
	 */
	void installSystem(Listener admin) throws SystemInstallFailedException;
}
