package lehrplanung.mb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.usecase.IExcelImportieren;
import de.lehrplanung.planung.usecase.ISemesterLaden;

@Named("importMB")
@RequestScoped
public class ImportMB {

	@Inject
	ISemesterLaden semesterLadenFacade;
	
	@Inject
	IExcelImportieren excelImportierenFacade;
	
	private SemesterTO semesterTO;
	List<SemesterTO> geladeneSemester = new ArrayList<>();
	
	private File uploadedFile;
	
//	ImportMB() {
//		ladeSemester();
//	}
	
	public List<String> ladeSemester () {
		geladeneSemester = semesterLadenFacade.semesterLaden();
		List<String> returnList = new ArrayList<String>();
		for(SemesterTO aSemesterTO: geladeneSemester) {
			if (aSemesterTO.isSommersemester()) {
				String returnString = "SoSe " + aSemesterTO.getJahr();
				returnList.add(returnString);
			} else {
				int jahrEnde = Integer.valueOf(aSemesterTO.getJahr()) + 1;
				String returnString = "WiSe " + aSemesterTO.getJahr() + "/" + jahrEnde;
				returnList.add(returnString);
			}
		}
		return returnList;
	}
	
	@PostConstruct
	public void initBean() {
		this.semesterTO = new SemesterTO();
	}
	
	public void upload() {
		System.out.println("test");
		excelImportierenFacade.excelImportieren(this.uploadedFile);
		this.initBean();
	}
	
	public String dateiImportAbbrechenClicked() {
		return "BACK_TO_HAUPTMENUE";
		
	}

	public File getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(File uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public List<SemesterTO> getGeladeneSemester() {
		return geladeneSemester;
	}

	public void setGeladeneSemester(List<SemesterTO> geladeneSemester) {
		this.geladeneSemester = geladeneSemester;
	}

	public SemesterTO getSemesterTO() {
		return semesterTO;
	}

	public void setSemesterTO(SemesterTO semesterTO) {
		this.semesterTO = semesterTO;
	}
	
}
