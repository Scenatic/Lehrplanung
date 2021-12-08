package de.lehrplanung.planung.usecase.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import de.lehrplanung.planung.dao.VeranstaltungDAO;
import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.entity.VeranstaltungTO;
import de.lehrplanung.planung.usecase.IExcelImportieren;

@Stateless
public class ExcelImportieren implements IExcelImportieren {

	@Inject
	VeranstaltungDAO veranstaltungDAO;
	
	@Override
	public List<VeranstaltungTO> excelImportieren(File file){
		
		List<VeranstaltungTO> returnList = new ArrayList<VeranstaltungTO>();
		
		try {
			file = new File("C:\\Users\\Niklas\\Desktop\\Studium\\5. Semester\\Softwareprojekt\\QuantitativeMethoden_SS2021.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator<Row> itr = sheet.iterator();
			while(itr.hasNext()) {
				Row row = itr.next();
				Cell cell;
				int i = 0;
				cell = row.getCell(i);
				int modulNr = Integer.valueOf(cell.getStringCellValue());
				i = i+1;
				cell = row.getCell(i);
				String modulName = cell.getStringCellValue();
				i = i+1;
				cell = row.getCell(i);
				int kursNr = Integer.valueOf(cell.getStringCellValue());
				i = i+1;
				cell = row.getCell(i);
				String kursName = cell.getStringCellValue();
				i = i+1;
				cell = row.getCell(i);
				String sprache = cell.getStringCellValue();
				i = i+1;
				cell = row.getCell(i);
				String studiengruppe = cell.getStringCellValue();
				i = i+1;
				cell = row.getCell(i);
				String dozent = cell.getStringCellValue();
				i = i+1;
				cell = row.getCell(i);
				boolean lehrbeauftragter = cell.getBooleanCellValue();
				i = i+1;
				cell = row.getCell(i);
				int anzahlLetztesSemester = Integer.valueOf(cell.getStringCellValue());
				i = i+1;
				cell = row.getCell(i);
				String bemerkung = cell.getStringCellValue();
				i = i+1;
				cell = row.getCell(i);
				String pruefungsform = cell.getStringCellValue();
				i = i+1;
				cell = row.getCell(i);
				double sws = cell.getNumericCellValue();
				i = i+1;
				cell = row.getCell(i);
				String turnus = cell.getStringCellValue();
				i = i+1;
				cell = row.getCell(i);
				String vertiefung = cell.getStringCellValue();
				i = i+1;
				cell = row.getCell(i);
				boolean modulAngelegt = cell.getBooleanCellValue();
				i = i+1;
				cell = row.getCell(i);
				boolean lvAngelegt = cell.getBooleanCellValue();
				i = i+1;
				cell = row.getCell(i);
				boolean modulanmeldung = cell.getBooleanCellValue();
				VeranstaltungTO aVeranstaltung = new VeranstaltungTO(
						modulNr, modulName, kursNr, kursName, sprache,
						studiengruppe, dozent, lehrbeauftragter, anzahlLetztesSemester, bemerkung,
						pruefungsform, sws, turnus, vertiefung, modulAngelegt,
						lvAngelegt, modulanmeldung);
				veranstaltungDAO.save(aVeranstaltung.toVeranstaltung());
				returnList.add(aVeranstaltung);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return returnList;
	}
	

}
