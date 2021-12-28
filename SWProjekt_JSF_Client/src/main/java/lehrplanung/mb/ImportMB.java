package lehrplanung.mb;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.usecase.IExcelImportieren;
import de.lehrplanung.planung.usecase.ISemesterLaden;

@Named("importMB")
@RequestScoped
public class ImportMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8373043828369027903L;

	@Inject
	ISemesterLaden semesterLadenFacade;
	
	@Inject
	IExcelImportieren excelImportierenFacade;
	
	private String semesterTOString;
	SemesterTO semesterTO;
	List<String> geladeneSemester = new ArrayList<>();
	
	Part uploadedFile;
	
	//angelegte Semester fuer List Auswahl laden
	public List<String> ladeSemester () {
		geladeneSemester = semesterLadenFacade.semesterLaden();
		return geladeneSemester;
	}
	
	@PostConstruct
	public void initBean() {
		this.semesterTO = new SemesterTO();
	}
	
	//Speichern der Excel Daten in DB starten
	public void upload() {
		System.out.println(this.semesterTOString);
		semesterTO = semesterLadenFacade.semesterFinden(this.semesterTOString);
		System.out.println("test");
		excelImportierenFacade.excelImportieren(this.uploadedFile, semesterTO);
		this.initBean();
	}
	
	public String dateiImportAbbrechenClicked() {
		return "BACK_TO_HAUPTMENUE";
		
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public List<String> getGeladeneSemester() {
		return geladeneSemester;
	}

	public void setGeladeneSemester(List<String> geladeneSemester) {
		this.geladeneSemester = geladeneSemester;
	}

	public SemesterTO getSemesterTO() {
		return semesterTO;
	}

	public void setSemesterTO(SemesterTO semesterTO) {
		this.semesterTO = semesterTO;
	}

	public String getSemesterTOString() {
		return semesterTOString;
	}

	public void setSemesterTOString(String semesterTOString) {
		this.semesterTOString = semesterTOString;
	}
	
}
