package de.lehrplanung.mitglieder.entity.impl;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import de.lehrplanung.mitglieder.entity.FGMitgliedTO;
import de.lehrplanung.mitglieder.entity.FachgruppeTO;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
@Table(name="SWProjekt_FGMitglied")
public class FGMitglied implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6131046864389652986L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SWPROJEKT_FGMITGLIEDER_NR")
	@SequenceGenerator(name="SWPROJEKT_FGMITGLIEDER_NR", sequenceName="SWPROJEKT_SEQ_FGMITGLIEDER_NR", allocationSize = 1)
	private int fgMitgliederId;
	
	private String name;
	private String vorname;
	private String eMail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fgId")
	private Fachgruppe fachgruppe;

	
	public FGMitglied() {
		
	}

	public FGMitglied(String name, String vorname, String eMail, Fachgruppe fachgruppe) {
		super();
		this.name = name;
		this.vorname = vorname;
		this.eMail = eMail;
		this.fachgruppe = fachgruppe;
	}
	
	public FGMitgliedTO toFGMitgliedTO(FachgruppeTO fachgruppeTO) {
		FGMitgliedTO fgMitgliedTO = new FGMitgliedTO();
		fgMitgliedTO.setName(this.name);
		fgMitgliedTO.setVorname(this.vorname);
		fgMitgliedTO.seteMail(this.eMail);
		//FachgruppeTO fachgruppeTO = this.fachgruppe.toFachgruppeTO();
		fgMitgliedTO.setFachgruppeTO(fachgruppeTO);
		return fgMitgliedTO;
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

	public Fachgruppe getFachgruppe() {
		return fachgruppe;
	}

	public void setFachgruppe(Fachgruppe fachgruppe) {
		this.fachgruppe = fachgruppe;
	}

	
}
