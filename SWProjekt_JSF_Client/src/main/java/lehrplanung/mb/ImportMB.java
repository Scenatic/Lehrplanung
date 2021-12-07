package lehrplanung.mb;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Part;

import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.usecase.ISemesterLaden;

@Named("importMB")
@RequestScoped
public class ImportMB {

	@Inject
	ISemesterLaden semesterLadenFacade;
	
	private SemesterTO semesterTO;
	List<SemesterTO> geladeneSemester = new ArrayList<>();
	
	private Part uploadedFile;
	
	ImportMB() {
		geladeneSemester = semesterLadenFacade.semesterLaden();
	}
	
	public void upload() {
		
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
}
