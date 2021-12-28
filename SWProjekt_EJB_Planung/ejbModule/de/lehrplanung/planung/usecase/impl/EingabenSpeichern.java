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
import de.lehrplanung.planung.usecase.IEingabenSpeichern;

@Stateless
public class EingabenSpeichern implements IEingabenSpeichern{
	
	@Inject
	SemesterDAO semesterDAO;
	
	@Inject
	VeranstaltungDAO veranstaltungDAO;
	
	List<Veranstaltung> returnList = new ArrayList<>();
	List<VeranstaltungTO> veranstaltungenTO = new ArrayList<>();
	
	@Override
	public void eingabenSpeichern(SemesterTO semesterTO) {
		Semester aSemester = semesterDAO.find(semesterTO.getSemesterId());
		returnList = aSemester.getVeranstaltungen();
		veranstaltungenTO = semesterTO.getVeranstaltungen();
		for (VeranstaltungTO aVeranstaltungTO : veranstaltungenTO) {
			Veranstaltung aVeranstaltung = aVeranstaltungTO.toVeranstaltung(aSemester);
			Veranstaltung veranstaltung = veranstaltungDAO.find(aVeranstaltung.getVeranstaltungsId());
			System.out.println(aVeranstaltung.getVeranstaltungsId());
			System.out.println(aVeranstaltung.getModulName());
			//try{
				veranstaltung.setDozent(aVeranstaltung.getDozent());
			//} catch(NullPointerException e) {
				
			//};
			//returnList.add(aVeranstaltung);
		}
		//aSemester.setVeranstaltungen(returnList);
		semesterDAO.update(aSemester);
	}
	
}
