package lehrplanung.mb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.usecase.ISemesterLaden;

@Named("importMB")
@RequestScoped
public class ImportMB {

	@Inject
	ISemesterLaden semesterLadenFacade;
	
	private SemesterTO semesterTO;
	List<SemesterTO> geladeneSemester = new ArrayList<>();
	
	private File uploadedFile;
	
	ImportMB() {
		//geladeneSemester = semesterLadenFacade.semesterLaden();
	}
	
	@PostConstruct
	public void initBean() {
		this.semesterTO = new SemesterTO();
	}
	
	public void upload() {
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
