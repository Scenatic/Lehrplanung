package lehrplanung.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.lehrplanung.mitglieder.entity.FachgruppeTO;
import de.lehrplanung.mitglieder.usecase.IFachgruppeLaden;
import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.usecase.ISemesterAnlegen;

@Named("semesterMB")
@RequestScoped
public class SemesterMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6743802069366250510L;

	@Inject
	ISemesterAnlegen semesterAnlegenFacade;	
	
	@Inject
	IFachgruppeLaden fachgruppeLadenFacade;
	
	private SemesterTO semesterTO;
	
	List<Integer> jahre = new ArrayList<>();
	List<String> geladeneFachgruppen = new ArrayList<>();
	String fachgruppeString;
	
	SemesterMB(){
		addJahre();
	}
	
	private void addJahre() {
		jahre.add(2021);
		jahre.add(2022);
		jahre.add(2023);
		jahre.add(2024);
		jahre.add(2025);
	}
	
	
	@PostConstruct
	public void initBean() {
		this.semesterTO = new SemesterTO();
	}
	
	//Fachgruppen fuer ListAuswahl laden
	public List<String>	ladeFachgruppen(){
		geladeneFachgruppen = fachgruppeLadenFacade.fachgruppeLaden();
		return geladeneFachgruppen;
		
	}
	
	public String semesterSpeichernClicked() {
		FachgruppeTO fachgruppeTO = fachgruppeLadenFacade.fachgruppeFinden(fachgruppeString);
		this.semesterTO.setFachgruppeTO(fachgruppeTO);
		semesterAnlegenFacade.semesterAnlegen(this.semesterTO);
		this.initBean();
		return "BACK_TO_HAUPTMENUE";
		
	}
	
	public String semesterAnlegenAbbrechenClicked() {
		return "BACK_TO_HAUPTMENUE";
		
	}

	public SemesterTO getSemesterTO() {
		return semesterTO;
	}

	public void setSemesterTO(SemesterTO semesterTO) {
		this.semesterTO = semesterTO;
	}

	public List<Integer> getJahre() {
		return jahre;
	}

	public void setJahre(List<Integer> jahre) {
		this.jahre = jahre;
	}

	public List<String> getGeladeneFachgruppen() {
		return geladeneFachgruppen;
	}

	public void setGeladeneFachgruppen(List<String> geladeneFachgruppen) {
		this.geladeneFachgruppen = geladeneFachgruppen;
	}

	public String getFachgruppeString() {
		return fachgruppeString;
	}

	public void setFachgruppeString(String fachgruppeString) {
		this.fachgruppeString = fachgruppeString;
	}
	
}
