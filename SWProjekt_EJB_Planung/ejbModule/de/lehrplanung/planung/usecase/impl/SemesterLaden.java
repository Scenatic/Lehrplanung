package de.lehrplanung.planung.usecase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.lehrplanung.planung.dao.SemesterDAO;
import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.entity.impl.Semester;
import de.lehrplanung.planung.usecase.ISemesterLaden;

@Stateless
public class SemesterLaden implements ISemesterLaden{

	@Inject
	SemesterDAO semesterDAO;
	
	@Override
	public List<SemesterTO> semesterLaden() {
		
		List<Semester> aList = semesterDAO.findAll();
		List<SemesterTO> returnList = new ArrayList<SemesterTO>();
		for (Semester aSemester : aList) returnList.add(aSemester.toSemesterTO());
		return returnList;
	}
	
}
