package de.lehrplanung.planung.entity;

import de.lehrplanung.planung.entity.impl.Semester;
import de.lehrplanung.planung.entity.impl.Veranstaltung;

public class VeranstaltungTO {

	int veranstaltungsId;
	String modulNr;
	String modulName;
	String kursNr;
	String kursName;
	String sprache;
	String studiengruppe;
	String dozent;
	boolean lehrbeauftragter;
	String anzahlLetztesSemester;
	String bemerkung;
	String pruefungsform;
	double sws;
	String turnus;
	String pflichtmodul;
	String vertiefung;
	boolean modulAngelegt;
	boolean lvAngelegt;
	boolean modulanmeldung;
	
	SemesterTO semesterTO;

	public VeranstaltungTO() {
		
	};
	
	public VeranstaltungTO(SemesterTO semesterTO, String modulNr, String modulName, String kursNr, String kursName, String sprache,
			String studiengruppe, String dozent, boolean lehrbeauftragter, String anzahlLetztesSemester, String bemerkung,
			String pruefungsform, double sws, String turnus, String pflichtmodul, String vertiefung, boolean modulAngelegt,
			boolean lvAngelegt, boolean modulanmeldung) {
		super();
		this.semesterTO = semesterTO;
		this.modulNr = modulNr;
		this.modulName = modulName;
		this.kursNr = kursNr;
		this.kursName = kursName;
		this.sprache = sprache;
		this.studiengruppe = studiengruppe;
		this.dozent = dozent;
		this.lehrbeauftragter = lehrbeauftragter;
		this.anzahlLetztesSemester = anzahlLetztesSemester;
		this.bemerkung = bemerkung;
		this.pruefungsform = pruefungsform;
		this.sws = sws;
		this.turnus = turnus;
		this.pflichtmodul = pflichtmodul;
		this.vertiefung = vertiefung;
		this.modulAngelegt = modulAngelegt;
		this.lvAngelegt = lvAngelegt;
		this.modulanmeldung = modulanmeldung;
	}

	public Veranstaltung toVeranstaltung(Semester semester) {
		Veranstaltung aVeranstaltung = new Veranstaltung();
		aVeranstaltung.setSemester(semester);
		aVeranstaltung.setVeranstaltungsId(this.veranstaltungsId);
		aVeranstaltung.setModulNr(this.modulNr);
		aVeranstaltung.setModulName(this.modulName);
		aVeranstaltung.setKursNr(this.kursNr);
		aVeranstaltung.setKursName(this.kursName);
		aVeranstaltung.setSprache(this.sprache);
		aVeranstaltung.setStudiengruppe(this.studiengruppe);
		aVeranstaltung.setDozent(this.dozent);
		aVeranstaltung.setLehrbeauftragter(this.lehrbeauftragter);
		aVeranstaltung.setAnzahlLetztesSemester(this.anzahlLetztesSemester);
		aVeranstaltung.setBemerkung(this.bemerkung);
		aVeranstaltung.setPruefungsform(this.pruefungsform);
		aVeranstaltung.setSws(this.sws);
		aVeranstaltung.setTurnus(this.turnus);
		aVeranstaltung.setPflichtmodul(this.pflichtmodul);
		aVeranstaltung.setVertiefung(this.vertiefung);
		aVeranstaltung.setModulAngelegt(this.modulAngelegt);
		aVeranstaltung.setLvAngelegt(this.lvAngelegt);
		aVeranstaltung.setModulanmeldung(this.modulanmeldung);
		return aVeranstaltung;
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

	public SemesterTO getSemesterTO() {
		return semesterTO;
	}

	public void setSemesterTO(SemesterTO semesterTO) {
		this.semesterTO = semesterTO;
	}

	public int getVeranstaltungsId() {
		return veranstaltungsId;
	}

	public void setVeranstaltungsId(int veranstaltungsId) {
		this.veranstaltungsId = veranstaltungsId;
	}
	
	
	
}
