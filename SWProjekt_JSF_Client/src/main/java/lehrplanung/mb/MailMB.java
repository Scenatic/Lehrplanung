package lehrplanung.mb;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.lehrplanung.link.usecase.ILinkVersenden;
import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.usecase.ISemesterLaden;

@Named("mailMB")
@RequestScoped
public class MailMB {

	@Inject
	ISemesterLaden semesterLadenFacade;
	
	@Inject
	ILinkVersenden linkVersendenFacade;
	
	List<String> geladeneSemester = new ArrayList<>();
	private String semesterTOString;
	private SemesterTO semesterTO;
	private String link;
	
	public List<String> ladeSemester () {
		geladeneSemester = semesterLadenFacade.semesterLaden();
		return geladeneSemester;
	}
	
	//Link in xhtml anzeigen
	public void linkErstellen() {
		this.semesterTO = semesterLadenFacade.semesterFinden(this.semesterTOString);
		setLink("http://localhost:8080/SWProjekt_JSF_Client/pages/public/VeranstaltungsEingabe.xhtml?semester="+this.semesterTO.getSemesterId());
	}	
	
	//EMail Versand mit Link zur Dateneingabe an FGMitglieder starten
	public void mailVersenden() {
		this.semesterTO = semesterLadenFacade.semesterFinden(this.semesterTOString);
		linkVersendenFacade.linkVersenden(this.semesterTO);
		
	}

	public List<String> getGeladeneSemester() {
		return geladeneSemester;
	}

	public String getSemesterTOString() {
		return semesterTOString;
	}

	public SemesterTO getSemesterTO() {
		return semesterTO;
	}

	public String getLink() {
		return link;
	}

	public void setGeladeneSemester(List<String> geladeneSemester) {
		this.geladeneSemester = geladeneSemester;
	}

	public void setSemesterTOString(String semesterTOString) {
		this.semesterTOString = semesterTOString;
	}

	public void setSemesterTO(SemesterTO semesterTO) {
		this.semesterTO = semesterTO;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
}
