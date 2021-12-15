package lehrplanung.mb;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.lehrplanung.planung.usecase.IExcelExportieren;

@Named("exportMB")
@RequestScoped
public class ExportMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1795473121152510394L;
	
	@Inject
	IExcelExportieren excelExportierenFacade;
	
	public void download() {
		//excelExportierenFacade.excelExportieren();
	}

}
