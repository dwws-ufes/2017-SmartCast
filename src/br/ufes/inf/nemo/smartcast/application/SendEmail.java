package br.ufes.inf.nemo.smartcast.application;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
//extends JSFController
@ManagedBean
public class SendEmail {
	private String nome; 
	private String fieldEmail; 
	private String mensagem;
	
	public String sendEmail () throws EmailException {
		//try {
			SimpleEmail email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(587);
			email.setAuthenticator(new DefaultAuthenticator("pablobrunetti1990@gmail.com", "pablo37224606"));
			email.setStartTLSEnabled(true);
			//Destino
			email.setFrom("pablobrunetti1990@gmail.com");
			//Assunto do Email
			email.setSubject("Redirecionado do Site Smartcast");
			//Mensagem enviada
			email.setMsg(mensagem);
			email.addTo(fieldEmail);
			email.send();
		///	addGlobalI18nMessage("smtCast", FacesMessage.SEVERITY_INFO, "email.button.send", "email.button.information");
			return null;
		//}
		//catch (EmailException e) {
         //   Logger.getLogger("ControllerEnvioEmail").log(Level.SEVERE,
              //      ">>>>>>>>>>>>>> Erro ao enviar email", e.getMessage());
           // addGlobalI18nMessage("smtCast", FacesMessage.SEVERITY_WARN, "email.button.sorry", "email.button.warning");
			
            //return null;
      //  }
    }

	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFieldEmail() {
		return fieldEmail;
	}


	public void setFieldEmail(String fieldEmail) {
		this.fieldEmail = fieldEmail;
	}


}
