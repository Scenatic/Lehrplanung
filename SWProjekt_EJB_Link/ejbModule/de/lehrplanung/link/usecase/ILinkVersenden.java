package de.lehrplanung.link.usecase;

import javax.ejb.Local;

import de.lehrplanung.planung.entity.SemesterTO;

@Local
public interface ILinkVersenden {

	void linkVersenden(SemesterTO semesterTO);

}
