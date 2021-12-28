package lehrplanung.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
//import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import de.lehrplanung.link.usecase.ILinkVersenden;
import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.entity.VeranstaltungTO;
import de.lehrplanung.planung.usecase.IEingabenSpeichern;
import de.lehrplanung.planung.usecase.IExcelExportieren;
import de.lehrplanung.planung.usecase.ISemesterLaden;
import de.lehrplanung.planung.usecase.IVeranstaltungenLaden;

@Named("veranstaltungMB")
//@RequestScoped
@SessionScoped
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
	
	@Inject
	IEingabenSpeichern eingabenSpeichernFacade;
	
	@Inject
	ILinkVersenden linkVersendenFacade;
	
	@ManagedProperty(value="#{param.semester}")
	private int urlId;
	
	private String semesterTOString;
	private SemesterTO semesterTO;
	List<String> geladeneSemester = new ArrayList<>();
	
	private VeranstaltungTO veranstaltungTO;
	List<VeranstaltungTO> veranstaltungen = new ArrayList<>();
	List<VeranstaltungTO> veranstaltungenListe = new ArrayList<>();
	
	//private HtmlDataTable datatable;
	
	private String link;
	
	public void onload() {
	    //veranstaltungTO = new VeranstaltungTO();
		this.urlId = getUrlId();
		veranstaltungenListe = starteVeranstaltungenEingabeLaden();
	}
	
	//Veranstaltungen fuer FGMitglieder laden
	@PostConstruct
	public void init() {
	    //veranstaltungTO = new VeranstaltungTO();
		//this.urlId = getUrlId();
		try {
			veranstaltungenListe = starteVeranstaltungenEingabeLaden();
		} catch (Exception e) {
			
		}
	}
	
	//angelegte Semester fuer List Auswahl laden
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
	
	//Uebersicht fuer FGSprecher anzeigen
	public void starteVeranstaltungenUebersichtLaden() {
		this.semesterTO = semesterLadenFacade.semesterFinden(this.semesterTOString);
		veranstaltungen = this.semesterTO.getVeranstaltungen();
	}
	
	//Eingabetabelle fuer FGMitglieder anzeigen
	//Wird in init() aufgerufen
	public List<VeranstaltungTO> starteVeranstaltungenEingabeLaden() {
		//urlId = getUrlId();
		
		//Parameter der SemesterId zuordnen
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		try {
			urlId = Integer.valueOf(req.getParameter("semester"));
		} catch (Exception e) {
			
		}
		System.out.println(urlId);
		//urlId = 161;
		
		//Semester auf Basis des Parameters/derSemesterId laden
		this.semesterTO = semesterLadenFacade.semesterFindenById(urlId);
		
		//setUrlId(this.urlId);
		//this.veranstaltungenListe = this.semesterTO.getVeranstaltungen();
		
		return this.semesterTO.getVeranstaltungen();
	}
	
	//Export der Daten aus DB in Excel starten
	public void download() {
		this.semesterTO = semesterLadenFacade.semesterFinden(this.semesterTOString);
		excelExportierenFacade.excelExportieren(this.semesterTO);
	}
	
	//Von FGMitglied eingegebene Daten speichern
	public void eingabeSpeichernClicked() {
		//urlId = getUrlId();
		System.out.println(this.urlId);
		this.semesterTO = semesterLadenFacade.semesterFindenById(this.urlId);
		this.semesterTO.setVeranstaltungen(veranstaltungenListe);
		System.out.println(veranstaltungenListe);
//		veranstaltungen = this.semesterTO.getVeranstaltungen();
//		for (VeranstaltungTO eineVeranstaltungTO:this.veranstaltungen) {
//			int i = 0;
//			datatable.setRows(i);
//			VeranstaltungTO veranstaltungTO = (VeranstaltungTO) datatable.getRowData();
//			eineVeranstaltungTO.setDozent(veranstaltungTO.getDozent());
//			System.out.println(veranstaltungTO.getDozent());
//			System.out.println("test");
//		}
		eingabenSpeichernFacade.eingabenSpeichern(this.semesterTO);
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

	public List getVeranstaltungenListe() {
		return veranstaltungenListe;
	}

	public void setVeranstaltungenListe(List veranstaltungenListe) {
		this.veranstaltungenListe = veranstaltungenListe;
	}

//	public HtmlDataTable getDatatable() {
//		return datatable;
//	}

//	public void setDatatable(HtmlDataTable datatable) {
//		this.datatable = datatable;
//	}
}
