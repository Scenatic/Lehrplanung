package de.security.facade;


import javax.ejb.Local;

import de.security.entity.User;

@Local
public interface IUserFacade {
	public User findUserByName(String username);
}