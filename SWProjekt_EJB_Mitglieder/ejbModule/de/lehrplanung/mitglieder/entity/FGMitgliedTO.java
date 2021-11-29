package de.lehrplanung.mitglieder.entity;

import java.io.Serializable;

public class FGMitgliedTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5686409918218500988L;
	
	String name;
	String vorname;
	String eMail;
	FachgruppeTO fachgruppeTO;
	
	public FGMitgliedTO() {
		
	}
	
	public FGMitgliedTO(String name, String vorname, String eMail, FachgruppeTO fachgruppeTO) {
		this.name = name;
		this.vorname = vorname;
		this.eMail = eMail;
		this.fachgruppeTO = fachgruppeTO;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public FachgruppeTO getFachgruppeTO() {
		return fachgruppeTO;
	}

	public void setFachgruppeTO(FachgruppeTO fachgruppeTO) {
		this.fachgruppeTO = fachgruppeTO;
	}
	
	

}
