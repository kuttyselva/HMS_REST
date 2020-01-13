package global.coda.hms.bean;

import java.util.List;

/**
 * The type Doctor patients list.
 */
public class DoctorPatientsList extends PatientRecord {
    /**
     * Gets record.
     *
     * @return the record
     */


    /**
     * The Doctor name.
     */
    private String doctorName;

    /**
     * Gets record.
     *
     * @return the record
     */
    public List<PatientRecord> getRecord() {
        return record;
    }

    /**
     * Sets record.
     *
     * @param record the record
     */
    public void setRecord(List<PatientRecord> record) {
        this.record = record;
    }

    /**
     * The Record.
     */
    private List<PatientRecord> record;

    /**
     * Gets doctor name.
     *
     * @return the doctor name
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * Sets doctor name.
     *
     * @param doctorName the doctor name
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
