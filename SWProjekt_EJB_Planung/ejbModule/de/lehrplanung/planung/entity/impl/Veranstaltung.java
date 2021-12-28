package de.lehrplanung.planung.entity.impl;

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
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.entity.VeranstaltungTO;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
@Table(name="SWProjekt_Veranstaltung")
@NamedQuery(name="Veranstaltung.ladeVeranstaltungen", 
query="SELECT v from Veranstaltung v where v.semester = :semesterId")
public class Veranstaltung implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6576656289768134367L;

	public static final String FIND_VERANSTALTUNGEN = "Veranstaltung.ladeVeranstaltungen";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SWPROJEKT_VERANSTALTUNG_NR")
	@SequenceGenerator(name="SWPROJEKT_VERANSTALTUNG_NR", sequenceName="SWPROJEKT_SEQ_VERANSTALTUNG_NR", allocationSize = 1)	
	private int veranstaltungsId;
	
	private String modulNr;
	private String modulName;
	private String kursNr;
	private String kursName;
	private String sprache;
	private String studiengruppe;
	private String dozent;
	private boolean lehrbeauftragter;
	private String anzahlLetztesSemester;
	private String bemerkung;
	private String pruefungsform;
	private double sws;
	private String turnus;
	private String pflichtmodul;
	private String vertiefung;
	private boolean modulAngelegt;
	private boolean lvAngelegt;
	private boolean modulanmeldung;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="semesterId")
	private Semester semester;

	public Veranstaltung() {
		
	}
	
	public VeranstaltungTO toVeranstaltungTO(SemesterTO semesterTO) {
		VeranstaltungTO aVeranstaltungTO = new VeranstaltungTO();
		aVeranstaltungTO.setSemesterTO(semesterTO);
		aVeranstaltungTO.setVeranstaltungsId(this.veranstaltungsId);
		aVeranstaltungTO.setModulNr(this.modulNr);
		aVeranstaltungTO.setModulName(this.modulName);
		aVeranstaltungTO.setKursNr(this.kursNr);
		aVeranstaltungTO.setKursName(this.kursName);
		aVeranstaltungTO.setSprache(this.sprache);
		aVeranstaltungTO.setStudiengruppe(this.studiengruppe);
		aVeranstaltungTO.setDozent(this.dozent);
		aVeranstaltungTO.setLehrbeauftragter(this.lehrbeauftragter);
		aVeranstaltungTO.setAnzahlLetztesSemester(this.anzahlLetztesSemester);
		aVeranstaltungTO.setBemerkung(this.bemerkung);
		aVeranstaltungTO.setPruefungsform(this.pruefungsform);
		aVeranstaltungTO.setSws(this.sws);
		aVeranstaltungTO.setTurnus(this.turnus);
		aVeranstaltungTO.setPflichtmodul(this.pflichtmodul);
		aVeranstaltungTO.setVertiefung(this.vertiefung);
		aVeranstaltungTO.setModulAngelegt(this.modulAngelegt);
		aVeranstaltungTO.setLvAngelegt(this.lvAngelegt);
		aVeranstaltungTO.setModulanmeldung(this.modulanmeldung);
		return aVeranstaltungTO;
	}

	public String getModulNr() {
		return modulNr;
	}

	public void setModulNr(String modulNr) {
		this.modulNr = modulNr;
	}

	public String getModulName() {
		return modulName;
	}

	public void setModulName(String modulName) {
		this.modulName = modulName;
	}

	public String getKursNr() {
		return kursNr;
	}

	public void setKursNr(String kursNr) {
		this.kursNr = kursNr;
	}

	public String getKursName() {
		return kursName;
	}

	public void setKursName(String kursName) {
		this.kursName = kursName;
	}

	public String getSprache() {
		return sprache;
	}

	public void setSprache(String sprache) {
		this.sprache = sprache;
	}

	public String getStudiengruppe() {
		return studiengruppe;
	}

	public void setStudiengruppe(String studiengruppe) {
		this.studiengruppe = studiengruppe;
	}

	public String getDozent() {
		return dozent;
	}

	public void setDozent(String dozent) {
		this.dozent = dozent;
	}

	public boolean isLehrbeauftragter() {
		return lehrbeauftragter;
	}

	public void setLehrbeauftragter(boolean lehrbeauftragter) {
		this.lehrbeauftragter = lehrbeauftragter;
	}

	public String getAnzahlLetztesSemester() {
		return anzahlLetztesSemester;
	}

	public void setAnzahlLetztesSemester(String anzahlLetztesSemester) {
		this.anzahlLetztesSemester = anzahlLetztesSemester;
	}

	public String getBemerkung() {
		return bemerkung;
	}

	public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
	}

	public String getPruefungsform() {
		return pruefungsform;
	}

	public void setPruefungsform(String pruefungsform) {
		this.pruefungsform = pruefungsform;
	}

	public double getSws() {
		return sws;
	}

	public void setSws(double sws) {
		this.sws = sws;
	}

	public String getTurnus() {
		return turnus;
	}

	public void setTurnus(String turnus) {
		this.turnus = turnus;
	}

	public String getPflichtmodul() {
		return pflichtmodul;
	}

	public void setPflichtmodul(String pflichtmodul) {
		this.pflichtmodul = pflichtmodul;
	}

	public String getVertiefung() {
		return vertiefung;
	}

	public void setVertiefung(String vertiefung) {
		this.vertiefung = vertiefung;
	}

	public boolean isModulAngelegt() {
		return modulAngelegt;
	}

	public void setModulAngelegt(boolean modulAngelegt) {
		this.modulAngelegt = modulAngelegt;
	}

	public boolean isLvAngelegt() {
		return lvAngelegt;
	}

	public void setLvAngelegt(boolean lvAngelegt) {
		this.lvAngelegt = lvAngelegt;
	}

	public boolean isModulanmeldung() {
		return modulanmeldung;
	}

	public void setModulanmeldung(boolean modulanmeldung) {
		this.modulanmeldung = modulanmeldung;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public int getVeranstaltungsId() {
		return veranstaltungsId;
	}

	public void setVeranstaltungsId(int veranstaltungsId) {
		this.veranstaltungsId = veranstaltungsId;
	}
	
}
