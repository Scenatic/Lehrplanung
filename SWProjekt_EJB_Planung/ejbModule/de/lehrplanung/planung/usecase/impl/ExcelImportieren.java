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
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
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
			DataFormatter formatter = new DataFormatter();
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator<Row> itr = sheet.iterator();
			//Row row = sheet.getRow(2);
			Row row = itr.next();
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
				VeranstaltungTO aVeranstaltung = new VeranstaltungTO(
						modulNr, modulName, kursNr, kursName, sprache,
						studiengruppe, dozent, lehrbeauftragter, anzahlLetztesSemester, bemerkung,
						pruefungsform, sws, turnus, pflichtmodul, vertiefung, modulAngelegt,
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
