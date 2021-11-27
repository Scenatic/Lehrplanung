package de.lehrplanung.mitglieder.dao;

import java.util.List;

import javax.ejb.Stateless;

import de.lehrplanung.mitglieder.entity.impl.FGMitglied;

@Stateless
public class FGMitgliedDAO extends GenericDAO<FGMitglied> {

	public FGMitgliedDAO() {
		super(FGMitglied.class);
	}
	
	public List<FGMitglied> fgMitgliederLaden() {
		
		return super.findAll();
	}
	
}
