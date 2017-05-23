package br.ufes.inf.nemo.smartcast.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

/**
 * A servlet that serves to invalidate the user's session and, therefore, log her out of the system.
 * 
 * @author Vitor E. Silva Souza (vitorsouza@gmail.com)
 */
//@WebServlet(name = "LogoutServlet", urlPatterns = { "/logout" })
@ManagedBean
public class LogoutServlet extends HttpServlet {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(LogoutServlet.class.getCanonicalName());

	/**
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String service() throws ServletException, IOException {
		logger.log(Level.FINER, "Invalidating a user session...");

		// Destroys the session for this user.
	    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		//HttpSession session = request.getSession(false);
		if (session != null) session.invalidate();
		return "/index.faces?faces-redirect=true";
		// Redirects back to the initial page.
		//response.sendRedirect(request.getContextPath());
	}
}
