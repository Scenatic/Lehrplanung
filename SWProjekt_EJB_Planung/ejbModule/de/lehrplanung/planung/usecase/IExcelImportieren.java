package de.lehrplanung.planung.usecase;

import java.util.List;

import javax.ejb.Local;

import de.lehrplanung.planung.entity.VeranstaltungTO;

@Local
public interface IExcelImportieren {

	public List<VeranstaltungTO> excelImportieren();
	
}
