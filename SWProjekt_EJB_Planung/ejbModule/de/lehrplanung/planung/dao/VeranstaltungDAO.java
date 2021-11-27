package de.lehrplanung.planung.dao;

import java.util.List;

import javax.ejb.Stateless;

import de.lehrplanung.planung.entity.impl.Veranstaltung;

@Stateless
public class VeranstaltungDAO extends GenericDAO<Veranstaltung> {

	public VeranstaltungDAO() {
		super(Veranstaltung.class);
	}
	
	public List<Veranstaltung> veranstaltungenLaden() {
		
		return super.findAll();
	}
	
}
