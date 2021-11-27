package de.security.facade;


import javax.inject.Inject;

import de.security.dao.UserDAO;
import de.security.entity.User;

import javax.ejb.Stateless;

@Stateless
public class UserFacadeImp implements IUserFacade {

	@Inject
	private UserDAO userDAO;
	
	public User findUserByName(String name) {
		return userDAO.findUserByName(name);
	}
}