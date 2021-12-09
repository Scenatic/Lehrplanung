package lehrplanung.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.entity.VeranstaltungTO;
import de.lehrplanung.planung.usecase.ISemesterLaden;

@Named("veranstaltungMB")
@RequestScoped
public class VeranstaltungMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6113292537758575980L;

	@Inject
	ISemesterLaden semesterLadenFacade;
	
	private SemesterTO semesterTO;
	List<String> geladeneSemester = new ArrayList<>();
	
	private VeranstaltungTO veranstaltungTO;
	
	public List<String> ladeSemester () {
		geladeneSemester = semesterLadenFacade.semesterLaden();
		return geladeneSemester;
	}
	
	public String eingabeSpeichernClicked() {
		return "BACK_TO_HAUPTMENUE";
	}
	
	public String eingabeAbbrechenClicked() {
		return "BACK_TO_HAUPTMENUE";
	}
	
//	public List<VeranstaltungTO> starteVeranstaltungenLaden() {
//		return ;
//	}
	
//	public List<VeranstaltungTO> starteVeranstaltungenUebersichtLaden() {
//		return ;
//	}
	
	public String starteVeranstaltungBearbeiten() {
		return "";
	}

	public VeranstaltungTO getVeranstaltungTO() {
		return veranstaltungTO;
	}

	public void setVeranstaltungTO(VeranstaltungTO veranstaltungTO) {
		this.veranstaltungTO = veranstaltungTO;
	}

	public SemesterTO getSemesterTO() {
		return semesterTO;
	}

	public void setSemesterTO(SemesterTO semesterTO) {
		this.semesterTO = semesterTO;
	}
}
