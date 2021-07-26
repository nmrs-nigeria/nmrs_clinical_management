package org.openmrs.module.nmrsclinicals.models;

import java.io.Serializable;
import java.util.Date;

public class PatientEncounterByType implements Serializable {
	
	private Integer encounterId;
	
	private String encounterType;
	
	private String formName;
	
	private Date encounterDateTime;
	
	private Integer visitId;
	
	private String formUUID;
	
	public PatientEncounterByType() {
	}
	
	public PatientEncounterByType(Integer encounterId, String encounterType, String formName, Date encounterDateTime,
	    Integer visitId, String formUUID) {
		this.encounterId = encounterId;
		this.encounterType = encounterType;
		this.formName = formName;
		this.encounterDateTime = encounterDateTime;
		this.visitId = visitId;
		this.formUUID = formUUID;
	}
	
	public Integer getEncounterId() {
		return encounterId;
	}
	
	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	
	public String getEncounterType() {
		return encounterType;
	}
	
	public void setEncounterType(String encounterType) {
		this.encounterType = encounterType;
	}
	
	public String getFormName() {
		return formName;
	}
	
	public void setFormName(String formName) {
		this.formName = formName;
	}
	
	public Date getEncounterDateTime() {
		return encounterDateTime;
	}
	
	public void setEncounterDateTime(Date encounterDateTime) {
		this.encounterDateTime = encounterDateTime;
	}
	
	public Integer getVisitId() {
		return visitId;
	}
	
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
	
	public String getFormUUID() {
		return formUUID;
	}
	
	public void setFormUUID(String formUUID) {
		this.formUUID = formUUID;
	}
	
	@Override
	public String toString() {
		return "PatientEncounterByType{" + "encounterId=" + encounterId + ", encounterType='" + encounterType + '\''
		        + ", formName='" + formName + '\'' + ", encounterDateTime=" + encounterDateTime + ", visitId=" + visitId
		        + ", formUUID='" + formUUID + '\'' + '}';
	}
}
