package de.lehrplanung.mitglieder.usecase;

import java.util.List;

import javax.ejb.Local;

import de.lehrplanung.mitglieder.entity.FachgruppeTO;

@Local
public interface IFachgruppeLaden {

	List<String> fachgruppeLaden();

	FachgruppeTO fachgruppeFinden(String fachgruppeTOString);

	FachgruppeTO fachgruppeFindenById(int fgId);

}
