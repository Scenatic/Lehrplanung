package de.lehrplanung.mitglieder.entity;

import java.io.Serializable;
import java.util.Collection;

import de.lehrplanung.mitglieder.entity.impl.FGMitglied;
import de.lehrplanung.mitglieder.entity.impl.Fachgruppe;

public class FachgruppeTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5575037859428229474L;
	
	int fgId;
	String fgName;
	private Collection<FGMitgliedTO> fgMitglieder;
	
	public FachgruppeTO(int fgId, String fgName) {
		super();
		this.fgId = fgId;
		this.fgName = fgName;
	}
	
	public Fachgruppe toFachgruppe() {
		Fachgruppe fachgruppe = new Fachgruppe(
				this.fgId,
				this.fgName);
		for (FGMitgliedTO fgMitgliedTO:this.fgMitglieder)
			fachgruppe.getFgMitglieder().add(
					new FGMitglied(fgMitgliedTO.getName(), fgMitgliedTO.getVorname(), fgMitgliedTO.geteMail(), fachgruppe));
		return fachgruppe;
	}
	
	
}
