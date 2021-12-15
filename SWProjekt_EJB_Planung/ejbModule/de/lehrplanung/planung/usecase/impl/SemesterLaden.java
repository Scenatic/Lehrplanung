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
	public List<String> semesterLaden() {
		
		List<Semester> aList = semesterDAO.findAll();
		List<SemesterTO> semesterTOList = new ArrayList<SemesterTO>();
		for (Semester aSemester : aList) semesterTOList.add(aSemester.toSemesterTO());
		List<String> returnList = new ArrayList<String>();
		for(SemesterTO aSemesterTO: semesterTOList) {
			if (aSemesterTO.isSommersemester()) {
				String returnString = aSemesterTO.getSemesterId() +":   SoSe " + aSemesterTO.getJahr();
				returnList.add(returnString);
			} else {
				int jahrEnde = Integer.valueOf(aSemesterTO.getJahr()) + 1;
				String returnString = aSemesterTO.getSemesterId() +":   WiSe " + aSemesterTO.getJahr() + "/" + jahrEnde;
				returnList.add(returnString);
			}
		}
		return returnList;
	}
	
	@Override
	public SemesterTO semesterFinden(String semesterTOString) {
		String[] semesterTOArray = semesterTOString.split(":");
		int semesterId = Integer.valueOf(semesterTOArray[0]);
		SemesterTO aSemesterTO = semesterDAO.find(semesterId).toSemesterTO();
		return aSemesterTO;
		
	}

	@Override
	public SemesterTO semesterFindenById(int urlId) {
		SemesterTO aSemesterTO = semesterDAO.find(urlId).toSemesterTO();
		return aSemesterTO;
	}
	
}
