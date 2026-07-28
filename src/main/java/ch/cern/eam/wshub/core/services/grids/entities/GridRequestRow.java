package ch.cern.eam.wshub.core.services.grids.entities;

import java.util.Arrays;

public class GridRequestRow  {

	private GridRequestCell[] cell;

    private String id;
    
	public GridRequestCell[] getCell() {
		return cell;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCells(GridRequestCell[] cell) {
		this.cell = cell;
	}

	@Override
	public String toString() {
		return "GridRequestRow [cell=" + Arrays.toString(cell) + ", id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(cell);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GridRequestRow other = (GridRequestRow) obj;
		if (!Arrays.equals(cell, other.cell))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
