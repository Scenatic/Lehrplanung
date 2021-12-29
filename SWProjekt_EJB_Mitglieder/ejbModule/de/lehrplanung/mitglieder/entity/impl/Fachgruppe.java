package de.lehrplanung.mitglieder.entity.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import de.lehrplanung.mitglieder.entity.FGMitgliedTO;
import de.lehrplanung.mitglieder.entity.FachgruppeTO;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
@Table(name="SWProjekt_Fachgruppe")
public class Fachgruppe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 927592988979212408L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SWPROJEKT_FG_NR")
	@SequenceGenerator(name="SWPROJEKT_FG_NR", sequenceName="SWPROJEKT_SEQ_FG_NR", allocationSize = 1)
	private int fgId;
	
	private String fgName;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="fgId")
	private List<FGMitglied> fgMitglieder;
	
	public Fachgruppe() {
		fgMitglieder = new ArrayList<FGMitglied>();
	}
	
	public Fachgruppe(int fgId, String fgName) {
		super();
		this.fgId = fgId;
		this.fgName = fgName;
		fgMitglieder = new ArrayList<FGMitglied>();
	}
	
	public FachgruppeTO toFachgruppeTO() {
		FachgruppeTO fachgruppeTO = new FachgruppeTO();
		fachgruppeTO.setFgId(this.fgId);
		fachgruppeTO.setFgName(this.fgName);
		fachgruppeTO.setFgMitglieder(new ArrayList<FGMitgliedTO>());
		for (FGMitglied fgMitglied:this.getFgMitglieder())
			fachgruppeTO.getFgMitglieder().add(fgMitglied.toFGMitgliedTO(fachgruppeTO));
		return fachgruppeTO;
	}
	

	public int getFgId() {
		return fgId;
	}

	public void setFgId(int fgId) {
		this.fgId = fgId;
	}

	public String getFgName() {
		return fgName;
	}

	public void setFgName(String fgName) {
		this.fgName = fgName;
	}

	public List<FGMitglied> getFgMitglieder() {
		return fgMitglieder;
	}

	public void setFgMitglieder(List<FGMitglied> fgMitglieder) {
		this.fgMitglieder = fgMitglieder;
	}
	
	
	
}
