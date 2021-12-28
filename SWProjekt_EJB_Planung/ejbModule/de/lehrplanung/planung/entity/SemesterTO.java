package de.lehrplanung.planung.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.lehrplanung.mitglieder.entity.FachgruppeTO;
import de.lehrplanung.planung.entity.impl.Semester;
import de.lehrplanung.planung.entity.impl.Veranstaltung;

public class SemesterTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2690125275182364873L;

	private int semesterId;
	private String jahr;
	private boolean sommersemester;
	private List<VeranstaltungTO> veranstaltungen;
	private FachgruppeTO fachgruppeTO;
	
	public SemesterTO() {
	}
	
	public SemesterTO(int semesterId, String jahr, boolean sommersemester, FachgruppeTO fachgruppeTO) {
		this.semesterId = semesterId;
		this.jahr = jahr;
		this.sommersemester = sommersemester;
		this.fachgruppeTO = fachgruppeTO;
	}
	
	public Semester toSemester(SemesterTO semesterTO) {
		
		Semester semester = new Semester(semesterTO);
		semester.setJahr(this.jahr);
		semester.setSommersemester(this.sommersemester);
		//veranstaltungen = new ArrayList<VeranstaltungTO>();
		for (VeranstaltungTO veranstaltungenTO:this.veranstaltungen)
			semester.getVeranstaltungen().add(veranstaltungenTO.toVeranstaltung(semester));
		semester.setFachgruppe(fachgruppeTO.toFachgruppe());
		return semester;
	}

	public int getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}

	public String getJahr() {
		return jahr;
	}

	public void setJahr(String jahr) {
		this.jahr = jahr;
	}

	public boolean isSommersemester() {
		return sommersemester;
	}

	public void setSommersemester(boolean sommersemester) {
		this.sommersemester = sommersemester;
	}

	public List<VeranstaltungTO> getVeranstaltungen() {
		return veranstaltungen;
	}

	public void setVeranstaltungen(List<VeranstaltungTO> veranstaltungen) {
		this.veranstaltungen = veranstaltungen;
	}

	public FachgruppeTO getFachgruppeTO() {
		return fachgruppeTO;
	}

	public void setFachgruppeTO(FachgruppeTO fachgruppeTO) {
		this.fachgruppeTO = fachgruppeTO;
	}
}
