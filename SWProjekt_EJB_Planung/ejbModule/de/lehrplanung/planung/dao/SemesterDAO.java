package de.lehrplanung.planung.dao;

import java.util.List;

import javax.ejb.Stateless;

import de.lehrplanung.planung.entity.impl.Semester;
import de.lehrplanung.planung.entity.impl.Veranstaltung;

@Stateless
public class SemesterDAO extends GenericDAO<Semester>{

	public SemesterDAO() {
		super(Semester.class);
	}
	
	public List<Semester> veranstaltungenLaden() {
		
		return super.findAll();
	}
	
}
