package de.lehrplanung.planung.entity;

import java.io.Serializable;
import java.util.Collection;

import de.lehrplanung.planung.entity.impl.Semester;

public class SemesterTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2690125275182364873L;

	private int semesterId;
	private String jahr;
	private boolean sommersemester;
	private Collection<VeranstaltungTO> veranstaltungen;
	
	public SemesterTO() {
	}
	
	public Semester toSemester() {
		
		Semester semester = new Semester();
		semester.setJahr(this.jahr);
		semester.setSommersemester(this.sommersemester);
		for (VeranstaltungTO veranstaltungenTO:this.veranstaltungen)
			semester.getVeranstaltungen();
		
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

	public Collection<VeranstaltungTO> getVeranstaltungen() {
		return veranstaltungen;
	}

	public void setVeranstaltungen(Collection<VeranstaltungTO> veranstaltungen) {
		this.veranstaltungen = veranstaltungen;
	}
}
