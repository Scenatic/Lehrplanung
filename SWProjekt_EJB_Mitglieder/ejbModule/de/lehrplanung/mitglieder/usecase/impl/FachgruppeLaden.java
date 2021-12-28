package de.lehrplanung.mitglieder.usecase.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.lehrplanung.mitglieder.dao.FachgruppeDAO;
import de.lehrplanung.mitglieder.entity.FachgruppeTO;
import de.lehrplanung.mitglieder.entity.impl.Fachgruppe;
import de.lehrplanung.mitglieder.usecase.IFachgruppeLaden;


@Stateless
public class FachgruppeLaden implements IFachgruppeLaden {
	
	@Inject
	FachgruppeDAO fachgruppeDAO;
	
	@Override
	public List<String> fachgruppeLaden() {
		
		List<Fachgruppe> aList = fachgruppeDAO.findAll();
		List<FachgruppeTO> fachgruppeTOList = new ArrayList<FachgruppeTO>();
		for (Fachgruppe aFachgruppe : aList) fachgruppeTOList.add(aFachgruppe.toFachgruppeTO());
		List<String> returnList = new ArrayList<String>();
		for(FachgruppeTO aFachgruppeTO: fachgruppeTOList) {
				String returnString = aFachgruppeTO.getFgId() +":" + aFachgruppeTO.getFgName();
				returnList.add(returnString);
			}
		return returnList;
	}
	
	@Override
	public FachgruppeTO fachgruppeFinden(String fachgruppeTOString) {
		String[] fachgruppeTOArray = fachgruppeTOString.split(":");
		int fgId = Integer.valueOf(fachgruppeTOArray[0]);
		FachgruppeTO aFachgruppeTO = fachgruppeDAO.find(fgId).toFachgruppeTO();
		return aFachgruppeTO;
	}
	
	@Override
	public FachgruppeTO fachgruppeFindenById(int fgId) {
		FachgruppeTO aFachgruppeTO = fachgruppeDAO.find(fgId).toFachgruppeTO();
		return aFachgruppeTO;
	}
	
}
