package lehrplanung.mb;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;


@Named("menueMB")
@RequestScoped
@RolesAllowed({"FGSPRECHER","MATERIALMANAGER"})
public class MenuMB {

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	SecurityContext securityContext;
	
	public MenuMB() {}
	
//	@RolesAllowed("FGSPRECHER")
//	public String starteSemesterAnlegen(){
//		
//		if (securityContext.isCallerInRole("FGSPRECHER")) {
//			System.out.println("Semester Anlegen");
//			return "SEMESTER_ANLEGEN";
//		} else {
//			System.out.println("Keine Rechte zum Anlegen eines Semesters");
//			return "STAY_ON_PAGE";	
//		}
//		
//		
//	}
	
	public String starteSemesterAnlegen() {
		return "SEMESTER_ANLEGEN";
	}
	
	public String starteDateiImportieren() {
		return "DATEI_IMPORT";
	}
	
	public String starteLinkVersenden() {
		return "LINK_VERSENDEN";
	}
	
	public String starteUebersicht() {
		return "UEBERSICHT_STARTEN";
	}
	
//	@RolesAllowed("MATERIALMANAGER")
//	public String starteMaterialmanagerMenue(){
//		
//		if (securityContext.isCallerInRole("MATERIALMANAGER")) {
//			System.out.println("Anzeigen Materialmanager Menue");
//			return "MATERIALMANAGER_MENUE";
//		} else {
//			System.out.println("Keine Rechte zum Anzeigen des Materialmanager Menues");
//			return "STAY_ON_PAGE";	
//		}
//
//	}



}
