package de.lehrplanung.planung.usecase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.lehrplanung.planung.dao.SemesterDAO;
import de.lehrplanung.planung.dao.VeranstaltungDAO;
import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.entity.VeranstaltungTO;
import de.lehrplanung.planung.entity.impl.Semester;
import de.lehrplanung.planung.entity.impl.Veranstaltung;
import de.lehrplanung.planung.usecase.IVeranstaltungenLaden;

@Stateless
public class VeranstaltungenLaden implements IVeranstaltungenLaden  {

	@Inject
	VeranstaltungDAO veranstaltungDAO;
	
	@Inject
	SemesterDAO semesterDAO;
	
	@Override
	public List<VeranstaltungTO> veranstaltungenLaden(int semesterId) {
		
		Semester aSemester = semesterDAO.find(semesterId);
		List<Veranstaltung> aList = veranstaltungDAO.veranstaltungenLaden(aSemester);
		SemesterTO aSemesterTO = aSemester.toSemesterTO();
		List<VeranstaltungTO> returnList = new ArrayList<VeranstaltungTO>();
		for (Veranstaltung aVeranstaltung : aList) returnList.add(aVeranstaltung.toVeranstaltungTO(aSemesterTO));
		return null;
	}

}
