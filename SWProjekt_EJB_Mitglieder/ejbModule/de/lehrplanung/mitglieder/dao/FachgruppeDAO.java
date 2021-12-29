package de.lehrplanung.mitglieder.dao;

import javax.ejb.Stateless;

import de.lehrplanung.mitglieder.entity.impl.Fachgruppe;

@Stateless
public class FachgruppeDAO extends GenericDAO<Fachgruppe>{
	
	public FachgruppeDAO() {
		super(Fachgruppe.class);
	}

}
