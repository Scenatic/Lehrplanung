package de.lehrplanung.mitglieder.usecase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.lehrplanung.mitglieder.dao.FGMitgliedDAO;
import de.lehrplanung.mitglieder.entity.FGMitgliedTO;
import de.lehrplanung.mitglieder.entity.impl.FGMitglied;
import de.lehrplanung.mitglieder.usecase.IMitgliederLaden;

@Stateless
public class MitgliederLaden implements IMitgliederLaden {

	@Inject
	FGMitgliedDAO fgMitgliedDAO;
	
	@Override
	public List<FGMitgliedTO> mitgliederLaden() {
		
		List<FGMitglied> aList = fgMitgliedDAO.findAll();
		List<FGMitgliedTO> returnList = new ArrayList<FGMitgliedTO>();
		for (FGMitglied aFGMitglied : aList) returnList.add(aFGMitglied.toFGMitgliedTO());
		return returnList;
	}

}
