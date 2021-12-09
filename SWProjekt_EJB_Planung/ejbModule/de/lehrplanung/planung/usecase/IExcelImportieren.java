package de.lehrplanung.planung.usecase;

import java.io.File;
import java.util.List;

import javax.ejb.Local;

import de.lehrplanung.planung.entity.SemesterTO;
import de.lehrplanung.planung.entity.VeranstaltungTO;

@Local
public interface IExcelImportieren {

	//public List<VeranstaltungTO> excelImportieren(File file);

	List<VeranstaltungTO> excelImportieren(File file, SemesterTO semesterTO);
	
}
