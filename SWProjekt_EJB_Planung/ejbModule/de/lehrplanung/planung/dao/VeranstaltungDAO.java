package de.lehrplanung.planung.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import de.lehrplanung.planung.entity.impl.Semester;
import de.lehrplanung.planung.entity.impl.Veranstaltung;

@Stateless
public class VeranstaltungDAO extends GenericDAO<Veranstaltung> {

	public VeranstaltungDAO() {
		super(Veranstaltung.class);
	}
	
	public List<Veranstaltung> veranstaltungenLaden(Semester aSemester) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("semesterId", aSemester);
		
		return super.findListResult(Veranstaltung.FIND_VERANSTALTUNGEN, parameters);
	}
	
}
