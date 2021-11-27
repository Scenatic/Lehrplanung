package lehrplanung.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import de.lehrplanung.planung.entity.SemesterTO;

@Named("semesterMB")
@RequestScoped
public class SemesterMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6743802069366250510L;

	private SemesterTO semesterTO;
	
	@PostConstruct
	public void initBean() {
		this.semesterTO = new SemesterTO();
	}
	
	public String semesterSpeichernClicked() {
		return "BACK_TO_MENUE";
		
	}
	
	public String semesterAnlegenAbbrechenClicked() {
		return "BACK_TO_MENUE";
		
	}

	public SemesterTO getSemesterTO() {
		return semesterTO;
	}

	public void setSemesterTO(SemesterTO semesterTO) {
		this.semesterTO = semesterTO;
	}
	
}
