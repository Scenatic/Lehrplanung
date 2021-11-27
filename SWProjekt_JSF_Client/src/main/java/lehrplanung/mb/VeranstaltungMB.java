package lehrplanung.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import de.lehrplanung.planung.entity.VeranstaltungTO;

@Named("veranstaltungMB")
@RequestScoped
public class VeranstaltungMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6113292537758575980L;

	private VeranstaltungTO veranstaltungTO;
	
	public String eingabeSpeichernClicked() {
		return "BACK_TO_MENUE";
	}
	
	public String eingabeAbbrechenClicked() {
		return "BACK_TO_MENUE";
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
}
