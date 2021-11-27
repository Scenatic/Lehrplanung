package de.lehrplanung.planung.usecase;

import javax.ejb.Local;

import de.lehrplanung.planung.entity.SemesterTO;

@Local
public interface ISemesterAnlegen {

	public void semesterAnlegen(SemesterTO semesterTO);
	
}
