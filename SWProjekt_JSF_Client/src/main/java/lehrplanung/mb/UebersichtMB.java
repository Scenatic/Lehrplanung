package lehrplanung.mb;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.entity.VeranstaltungTO;
import de.lehrplanung.planung.usecase.IExcelExportieren;
import de.lehrplanung.planung.usecase.ISemesterLaden;

@Named("uebersichtMB")
@RequestScoped
public class UebersichtMB {

	@Inject
	ISemesterLaden semesterLadenFacade;
	
	@Inject
	IExcelExportieren excelExportierenFacade;
	
	private String semesterTOString;
	private SemesterTO semesterTO;
	List<String> geladeneSemester = new ArrayList<>();
	
	private VeranstaltungTO veranstaltungTO;
	List<VeranstaltungTO> veranstaltungen = new ArrayList<>();
	
	//angelegte Semester fuer List Auswahl laden
	public List<String> ladeSemester () {
		geladeneSemester = semesterLadenFacade.semesterLaden();
		return geladeneSemester;
	}
	
	//Uebersicht fuer FGSprecher anzeigen
	public void starteVeranstaltungenUebersichtLaden() {
		this.semesterTO = semesterLadenFacade.semesterFinden(this.semesterTOString);
		veranstaltungen = this.semesterTO.getVeranstaltungen();
	}
	
	//Export der Daten aus DB in Excel starten
	public void download() {
		this.semesterTO = semesterLadenFacade.semesterFinden(this.semesterTOString);
		excelExportierenFacade.excelExportieren(this.semesterTO);
	}
	
	public String uebersichtAbbrechenClicked() {
		return "BACK_TO_HAUPTMENUE";
	}

	public String getSemesterTOString() {
		return semesterTOString;
	}

	public SemesterTO getSemesterTO() {
		return semesterTO;
	}

	public List<String> getGeladeneSemester() {
		return geladeneSemester;
	}

	public VeranstaltungTO getVeranstaltungTO() {
		return veranstaltungTO;
	}

	public List<VeranstaltungTO> getVeranstaltungen() {
		return veranstaltungen;
	}

	public void setSemesterTOString(String semesterTOString) {
		this.semesterTOString = semesterTOString;
	}

	public void setSemesterTO(SemesterTO semesterTO) {
		this.semesterTO = semesterTO;
	}

	public void setGeladeneSemester(List<String> geladeneSemester) {
		this.geladeneSemester = geladeneSemester;
	}

	public void setVeranstaltungTO(VeranstaltungTO veranstaltungTO) {
		this.veranstaltungTO = veranstaltungTO;
	}

	public void setVeranstaltungen(List<VeranstaltungTO> veranstaltungen) {
		this.veranstaltungen = veranstaltungen;
	}
	
}
