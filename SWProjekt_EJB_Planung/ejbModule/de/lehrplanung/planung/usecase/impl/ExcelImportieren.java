package de.lehrplanung.planung.usecase.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.Part;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import de.lehrplanung.planung.dao.SemesterDAO;
import de.lehrplanung.planung.dao.VeranstaltungDAO;
import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.entity.VeranstaltungTO;
import de.lehrplanung.planung.entity.impl.Semester;
import de.lehrplanung.planung.entity.impl.Veranstaltung;
import de.lehrplanung.planung.usecase.IExcelImportieren;

@Stateless
public class ExcelImportieren implements IExcelImportieren {

	@Inject
	VeranstaltungDAO veranstaltungDAO;
	
	@Inject
	SemesterDAO semesterDAO;
	
	@Override
	public List<VeranstaltungTO> excelImportieren(Part file, SemesterTO semesterTO){
		
		List<VeranstaltungTO> returnList = new ArrayList<VeranstaltungTO>();
		
		try {
			//file = new File("C:\\Users\\Niklas\\Desktop\\Studium\\5. Semester\\Softwareprojekt\\QuantitativeMethoden_SS2021.xlsx");
			//FileInputStream fis = new FileInputStream(file);
			
			//DataFormatter um die ExcelDatentypen den Java Datentypen anzupassen
			DataFormatter formatter = new DataFormatter();
			
			//InputStream fuer die ExcelDatei
			InputStream fis = file.getInputStream();
			
			//Excel Datei und Tabellenblatt waehlen
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			
			//Iterator fuer nachfolgende Schleife
			Iterator<Row> itr = sheet.iterator();
			//Row row = sheet.getRow(2);
			Row row = itr.next();
			
			//Schleife um Zellenwerte den Variablen zuzuordnen
			while(itr.hasNext()) {
				row = itr.next();
				Cell cell;
				int i = 0;
				cell = row.getCell(i);
				String modulNr = formatter.formatCellValue(cell);
				i = i+1;
				cell = row.getCell(i);
				String modulName = formatter.formatCellValue(cell);
				i = i+1;
				cell = row.getCell(i);
				String kursNr = formatter.formatCellValue(cell);
				i = i+1;
				cell = row.getCell(i);
				String kursName = formatter.formatCellValue(cell);
				i = i+1;
				cell = row.getCell(i);
				String sprache = formatter.formatCellValue(cell);
				i = i+1;
				cell = row.getCell(i);
				String studiengruppe = formatter.formatCellValue(cell);
				i = i+1;
				cell = row.getCell(i);
				String dozent = formatter.formatCellValue(cell);
				i = i+1;
				cell = row.getCell(i);
				boolean lehrbeauftragter = true;
				if (cell == null || cell.getCellTypeEnum() == CellType.BLANK) {
					lehrbeauftragter = false;
				}
				i = i+1;
				cell = row.getCell(i);
				String anzahlLetztesSemester = formatter.formatCellValue(cell);
				i = i+1;
				cell = row.getCell(i);
				String bemerkung = formatter.formatCellValue(cell);
				i = i+1;
				cell = row.getCell(i);
				String pruefungsform = formatter.formatCellValue(cell);
				i = i+1;
				cell = row.getCell(i);
				double sws = 0;
				if (cell == null || cell.getCellTypeEnum() == CellType.BLANK) {
					sws = 0;
				} else {
					sws = cell.getNumericCellValue();
				}
				i = i+1;
				cell = row.getCell(i);
				String turnus = formatter.formatCellValue(cell);
				i = i+1;
				cell = row.getCell(i);
				String pflichtmodul = formatter.formatCellValue(cell);
				i = i+1;
				cell = row.getCell(i);
				String vertiefung = formatter.formatCellValue(cell);
				i = i+1;
				cell = row.getCell(i);
				boolean modulAngelegt = true;
				if (cell == null || cell.getCellTypeEnum() == CellType.BLANK) {
					modulAngelegt = false;
				}
				i = i+1;
				cell = row.getCell(i);
				boolean lvAngelegt = true;
				if (cell == null || cell.getCellTypeEnum() == CellType.BLANK) {
					lvAngelegt = false;
				}
				i = i+1;
				cell = row.getCell(i);
				boolean modulanmeldung = true;
				if (cell == null || cell.getCellTypeEnum() == CellType.BLANK) {
					modulanmeldung = false;
				}
				
				//VeranstaltungTO aus den Variablen erstellen
				VeranstaltungTO aVeranstaltungTO = new VeranstaltungTO(semesterTO,
						modulNr, modulName, kursNr, kursName, sprache,
						studiengruppe, dozent, lehrbeauftragter, anzahlLetztesSemester, bemerkung,
						pruefungsform, sws, turnus, pflichtmodul, vertiefung, modulAngelegt,
						lvAngelegt, modulanmeldung);
				
				//angegebenes Semester laden
				Semester aSemester = semesterDAO.find(semesterTO.getSemesterId());
				
				//Veranstaltung aus TO generieren
				Veranstaltung aVeranstaltung = aVeranstaltungTO.toVeranstaltung(aSemester);
				
				//System.out.println(aVeranstaltung.getModulName());
				//System.out.println(aSemester.getJahr());
				
				//Veranstaltung dem Semester hinzufuegen
				aSemester.addVeranstaltungen(aVeranstaltung);
				
				//.add(aVeranstaltung);
				//semesterDAO.save(aSemester);
				
				//Erstellte und dem Semester zugeordnete Veranstaltung in DB speichern
				semesterDAO.update(aSemester);
				
				//veranstaltungDAO.save(aVeranstaltungTO.toVeranstaltung(aSemester));
				returnList.add(aVeranstaltungTO);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return returnList;
	}
	

}
