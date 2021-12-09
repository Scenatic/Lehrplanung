package de.lehrplanung.planung.usecase;

import java.util.List;

import javax.ejb.Local;

import de.lehrplanung.planung.entity.SemesterTO;

@Local
public interface ISemesterLaden {

	public List<String> semesterLaden();
	
}
