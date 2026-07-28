package ch.cern.eam.wshub.core.services.grids.entities;

public class GridDDSpyFieldsResult  {

	private GridField[] gridFields;
	private String dataSpyId;
	
	public String getDataSpyId() {
		return dataSpyId;
	}
	public void setDataSpyId(String dataSpyId) {
		this.dataSpyId = dataSpyId;
	}

	public GridField[] getGridFields() {
		return gridFields;
	}
	public void setGridFields(GridField[] gridFields) {
		this.gridFields = gridFields;
	}
	
}
