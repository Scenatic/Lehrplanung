package lehrplanung.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
//import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.entity.VeranstaltungTO;
import de.lehrplanung.planung.usecase.IExcelExportieren;
import de.lehrplanung.planung.usecase.ISemesterLaden;
import de.lehrplanung.planung.usecase.IVeranstaltungenLaden;

@Named("veranstaltungMB")
@RequestScoped
public class VeranstaltungMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6113292537758575980L;

	@Inject
	ISemesterLaden semesterLadenFacade;
	
	@Inject
	IVeranstaltungenLaden veranstaltungenLadenFacade;
	
	@Inject
	IExcelExportieren excelExportierenFacade;
	
	@ManagedProperty(value="#{param.semester}")
	private int urlId;
	
	private String semesterTOString;
	private SemesterTO semesterTO;
	List<String> geladeneSemester = new ArrayList<>();
	
	private VeranstaltungTO veranstaltungTO;
	List<VeranstaltungTO> veranstaltungen = new ArrayList<>();
	
	private String link;
	
	public List<String> ladeSemester () {
		geladeneSemester = semesterLadenFacade.semesterLaden();
		return geladeneSemester;
	}
	
//	public List<VeranstaltungTO> starteVeranstaltungenUebersichtLaden() {
//		semesterTO = semesterLadenFacade.semesterFinden(this.semesterTOString);
//		System.out.println(semesterTO.getSemesterId());
//		return veranstaltungenLadenFacade.veranstaltungenLaden(semesterTO.getSemesterId());
//		
//	}

	public void linkErstellen() {
		this.semesterTO = semesterLadenFacade.semesterFinden(this.semesterTOString);
		setLink("http://localhost:8080/SWProjekt_JSF_Client/pages/public/VeranstaltungsEingabe.xhtml?semester="+this.semesterTO.getSemesterId());
	}
	
	public void starteVeranstaltungenUebersichtLaden() {
		this.semesterTO = semesterLadenFacade.semesterFinden(this.semesterTOString);
	}
	
	public List<VeranstaltungTO> starteVeranstaltungenEingabeLaden() {
		System.out.println(this.urlId);
		//urlId = 91;
		this.semesterTO = semesterLadenFacade.semesterFindenById(this.urlId);
		return this.semesterTO.getVeranstaltungen();
	}
	
	public void download() {
		this.semesterTO = semesterLadenFacade.semesterFinden(this.semesterTOString);
		excelExportierenFacade.excelExportieren(this.semesterTO);
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

	public List<VeranstaltungTO> getVeranstaltungen() {
		return veranstaltungen;
	}

	public void setVeranstaltungen(List<VeranstaltungTO> veranstaltungen) {
		this.veranstaltungen = veranstaltungen;
	}

	public String getSemesterTOString() {
		return semesterTOString;
	}

	public void setSemesterTOString(String semesterTOString) {
		this.semesterTOString = semesterTOString;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getUrlId() {
		return urlId;
	}

	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}
}
