package de.lehrplanung.planung.usecase;

import javax.ejb.Local;

@Local
public interface IExcelExportieren {

	public void excelExportieren(int i);
	
}
