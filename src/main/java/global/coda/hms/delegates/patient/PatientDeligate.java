package global.coda.hms.delegates.patient;

import global.coda.hms.bean.PatientRecord;
import global.coda.hms.helpers.patient.PatientHelper;

/**
 * The type Patient deligate.
 */
public class PatientDeligate {
    private final PatientHelper patientHelper = new PatientHelper();

    /**
     * Create patient boolean.
     *
     * @param record the record
     * @return the boolean
     */
    public boolean createPatient(PatientRecord record) {
        return patientHelper.createPatient(record);
    }

    /**
     * Update patient boolean.
     *
     * @param record the record
     * @return the boolean
     */
    public boolean updatePatient(PatientRecord record) {
        return patientHelper.updateUser(record);
    }

    /**
     * Read patient patient record.
     *
     * @param patientName the patient name
     * @return the patient record
     */
    public PatientRecord readPatient(String patientName) {
        return patientHelper.readPatient(patientName);
    }

}
