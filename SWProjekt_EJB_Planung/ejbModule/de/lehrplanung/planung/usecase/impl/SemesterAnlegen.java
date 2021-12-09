package de.lehrplanung.planung.usecase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.lehrplanung.planung.dao.SemesterDAO;
import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.entity.impl.Semester;
import de.lehrplanung.planung.entity.impl.Veranstaltung;
import de.lehrplanung.planung.usecase.ISemesterAnlegen;

@Stateless
public class SemesterAnlegen implements ISemesterAnlegen {

	@Inject
	SemesterDAO semesterDAO;	
	
	private List<Veranstaltung> veranstaltungen;
	
	@Override
	public void semesterAnlegen(SemesterTO semesterTO) {
		Semester aSemester = new Semester(semesterTO);
		semesterDAO.save(aSemester);
		
	}

	
}
