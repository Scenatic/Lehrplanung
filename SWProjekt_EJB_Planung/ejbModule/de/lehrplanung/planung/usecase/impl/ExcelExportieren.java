package de.lehrplanung.planung.usecase.impl;

import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

import de.lehrplanung.planung.dao.VeranstaltungDAO;
import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.entity.VeranstaltungTO;
import de.lehrplanung.planung.entity.impl.Semester;
import de.lehrplanung.planung.entity.impl.Veranstaltung;
import de.lehrplanung.planung.usecase.IExcelExportieren;

@Stateless
public class ExcelExportieren implements IExcelExportieren {

	@Inject
	VeranstaltungDAO veranstaltungDAO;
	
	@Override
	public void excelExportieren(SemesterTO semesterTO) {
		
		try {
			String filename = "export.xls";
			String contentType = "application/vnd.ms-excel";
			//filename = "C:\\Users\\Niklas\\Desktop\\Studium\\5. Semester\\Softwareprojekt\\Export.xls";
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Tabelle 1");
			
			HSSFRow rowhead = sheet.createRow((short)0);
			rowhead.createCell(0).setCellValue("ModulNr");
			rowhead.createCell(1).setCellValue("Modulname");
			rowhead.createCell(2).setCellValue("kursNr");
			rowhead.createCell(3).setCellValue("Kursname");
			rowhead.createCell(4).setCellValue("Sprache");
			rowhead.createCell(5).setCellValue("Studiengruppe");
			rowhead.createCell(6).setCellValue("Dozent");
			rowhead.createCell(7).setCellValue("Lehrbeauftragter");
			rowhead.createCell(8).setCellValue("AnzahlLetztesSemester");
			rowhead.createCell(9).setCellValue("Bemerkung");
			rowhead.createCell(10).setCellValue("Pruefungsform");
			rowhead.createCell(11).setCellValue("SWS");
			rowhead.createCell(12).setCellValue("Turnus");
			rowhead.createCell(13).setCellValue("Pflichtmodule");
			rowhead.createCell(14).setCellValue("Vertiefung");
			rowhead.createCell(15).setCellValue("ModulAngelegt");
			rowhead.createCell(16).setCellValue("LVAngelegt");
			rowhead.createCell(17).setCellValue("Modulanmeldung");
			
//			Semester a = new Semester();
//			List<Veranstaltung> aList = veranstaltungDAO.veranstaltungenLaden(a);
			List<VeranstaltungTO> aList = semesterTO.getVeranstaltungen();
			int i = 0;
			for(VeranstaltungTO aVeranstaltung : aList) {
				int j = 0;
				i = i + 1;
				HSSFRow row = sheet.createRow((short)i);
				row.createCell(j).setCellValue(aVeranstaltung.getModulNr());
				j = j +1;
				row.createCell(j).setCellValue(aVeranstaltung.getModulName());
				j = j +1;
				row.createCell(j).setCellValue(aVeranstaltung.getKursNr());
				j = j +1;
				row.createCell(j).setCellValue(aVeranstaltung.getKursName());
				j = j +1;
				row.createCell(j).setCellValue(aVeranstaltung.getSprache());
				j = j +1;
				row.createCell(j).setCellValue(aVeranstaltung.getStudiengruppe());
				j = j +1;
				row.createCell(j).setCellValue(aVeranstaltung.getDozent());
				j = j +1;
				row.createCell(j).setCellValue(aVeranstaltung.isLehrbeauftragter());
				j = j +1;
				row.createCell(j).setCellValue(aVeranstaltung.getAnzahlLetztesSemester());
				j = j +1;
				row.createCell(j).setCellValue(aVeranstaltung.getBemerkung());
				j = j +1;
				row.createCell(j).setCellValue(aVeranstaltung.getPruefungsform());
				j = j +1;
				row.createCell(j).setCellValue(aVeranstaltung.getSws());
				j = j +1;
				row.createCell(j).setCellValue(aVeranstaltung.getTurnus());
				j = j +1;
				row.createCell(j).setCellValue(aVeranstaltung.getVertiefung());
				j = j +1;
				row.createCell(j).setCellValue(aVeranstaltung.isModulAngelegt());
				j = j +1;
				row.createCell(j).setCellValue(aVeranstaltung.isLvAngelegt());
				j = j +1;
				row.createCell(j).setCellValue(aVeranstaltung.isModulanmeldung());
			}
	
			
			//FileOutputStream fileOut = new FileOutputStream(filename);
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			ec.responseReset();
			ec.setResponseContentType(contentType);
			ec.setResponseHeader("Content-Disposition", "attachment; filename=\""+filename+"\"");
			OutputStream fileOut = ec.getResponseOutputStream();
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
			fc.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
