package global.coda.hms.bean;

import java.util.List;

/**
 * The type Doctor record.
 *
 * @author VC
 */
public class DoctorRecord extends UserRecord {

    private String speciality;

	/**
	 * Gets patient list.
	 *
	 * @return the patient list
	 */
	public List<PatientRecord> getPatientList() {
		return patientList;
	}

	/**
	 * Sets patient list.
	 *
	 * @param patientList the patient list
	 */
	public void setPatientList(List<PatientRecord> patientList) {
		this.patientList = patientList;
	}

	private List<PatientRecord> patientList;


	/**
	 * gets speciality.
	 *
	 * @return speciality job of doc.
	 */
	public String getSpeciality() {
        return speciality;
    }

	/**
	 * sets age.
	 *
	 * @param speciality user job.
	 */
	public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}

