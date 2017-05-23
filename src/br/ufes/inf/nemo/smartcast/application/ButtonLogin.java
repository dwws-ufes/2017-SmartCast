package br.ufes.inf.nemo.smartcast.application;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ButtonLogin {
	 public String goLoginPage() {
	        return "index?faces-redirect=true";
	    }
	     

}
