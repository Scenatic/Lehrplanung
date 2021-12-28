package de.lehrplanung.planung.entity.impl;

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

import de.lehrplanung.mitglieder.entity.impl.Fachgruppe;
import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.entity.VeranstaltungTO;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
@Table(name="SWProjekt_Semester")
public class Semester implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5119502244113043028L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SWPROJEKT_SEMESTER_NR")
	@SequenceGenerator(name="SWPROJEKT_SEMESTER_NR", sequenceName="SWPROJEKT_SEQ_SEMESTER_NR", allocationSize = 1)
	private int semesterId;
	
	private String jahr;
	private boolean sommersemester;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="semesterId")
	private List<Veranstaltung> veranstaltungen;
	
	private Fachgruppe fachgruppe;
	
	public SemesterTO toSemesterTO() {
		SemesterTO semesterTO = new SemesterTO(
//				this.getSemesterId(),
//				this.getJahr(),
//				this.isSommersemester()
				);
		semesterTO.setSemesterId(this.semesterId);
		semesterTO.setJahr(this.jahr);
		semesterTO.setSommersemester(this.sommersemester);
		semesterTO.setVeranstaltungen(new ArrayList<VeranstaltungTO>());
		for (Veranstaltung eineVeranstaltung:this.getVeranstaltungen())
			semesterTO.getVeranstaltungen().add(eineVeranstaltung.toVeranstaltungTO(semesterTO));
		semesterTO.setFachgruppeTO(fachgruppe.toFachgruppeTO());
		return semesterTO;
	}
	
	public Semester() {
	}
	
	public Semester(SemesterTO semesterTO) {
		this.jahr = semesterTO.getJahr();
		this.sommersemester = semesterTO.isSommersemester();
		veranstaltungen = new ArrayList<Veranstaltung>();
		this.fachgruppe = semesterTO.getFachgruppeTO().toFachgruppe();
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

	public int getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}

	public List<Veranstaltung> getVeranstaltungen() {
		return veranstaltungen;
	}

	public void setVeranstaltungen(List<Veranstaltung> veranstaltungen) {
		this.veranstaltungen = veranstaltungen;
	}

	public void addVeranstaltungen(Veranstaltung aVeranstaltung) {
		this.veranstaltungen.add(aVeranstaltung);
		
	}

	public Fachgruppe getFachgruppe() {
		return fachgruppe;
	}

	public void setFachgruppe(Fachgruppe fachgruppe) {
		this.fachgruppe = fachgruppe;
	}
	
	
}
