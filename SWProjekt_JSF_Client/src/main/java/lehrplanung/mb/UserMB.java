package lehrplanung.mb;

import java.io.IOException;
import java.io.Serializable;


import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;

import de.security.facade.IUserFacade;
import de.security.entity.User;


@SessionScoped
@Named("userMB")
//@RolesAllowed({"ADMIN","USER"})
public class UserMB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4350145920139285844L;

	private User user;
	
	@Inject
	private IUserFacade userFacade;
	
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	SecurityContext securityContext;
	
	public UserMB() {}
	
	public User getUser(){
		
		System.out.println("getUser() in UserMB called");
		if(user == null){
			
			System.out.println("User hat Rolle FGSprecher: "+securityContext.isCallerInRole("FGSPRECHER"));
			String username = securityContext.getCallerPrincipal().getName();
			user = userFacade.findUserByName(username);
		}
		
		return user;
	}
	
	public boolean isUserFGSprecher(){
		return securityContext.isCallerInRole("FGSprecher");
	}
	
	public String logOut() throws IOException, ServletException{
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.invalidateSession();
		try {
			ec.redirect(ec.getApplicationContextPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Logout; USER: "+securityContext.getCallerPrincipal().getName());
		return "LOGOUT";
	}
	
}