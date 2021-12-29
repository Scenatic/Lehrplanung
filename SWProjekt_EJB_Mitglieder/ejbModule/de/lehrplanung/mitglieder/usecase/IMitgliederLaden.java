package de.lehrplanung.mitglieder.usecase;

import java.util.List;

import javax.ejb.Local;

import de.lehrplanung.mitglieder.entity.FGMitgliedTO;
import de.lehrplanung.mitglieder.entity.FachgruppeTO;

@Local
public interface IMitgliederLaden {

	public List<FGMitgliedTO> mitgliederLaden(FachgruppeTO fachgruppeTO);
	
}
