package de.lehrplanung.planung.usecase.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.lehrplanung.planung.dao.SemesterDAO;
import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.entity.impl.Semester;
import de.lehrplanung.planung.usecase.ISemesterAnlegen;

@Stateless
public class SemesterAnlegen implements ISemesterAnlegen {

	@Inject
	SemesterDAO semesterDAO;	
	
	@Override
	public void semesterAnlegen(SemesterTO semesterTO) {
		Semester aSemester = new Semester();
		aSemester = semesterTO.toSemester();
		semesterDAO.save(aSemester);
		
	}

	
}
