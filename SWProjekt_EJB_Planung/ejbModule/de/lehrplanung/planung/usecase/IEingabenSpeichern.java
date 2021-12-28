package de.lehrplanung.planung.usecase;

import javax.ejb.Local;

import de.lehrplanung.planung.entity.SemesterTO;

@Local
public interface IEingabenSpeichern {

	void eingabenSpeichern(SemesterTO semesterTO);

}
