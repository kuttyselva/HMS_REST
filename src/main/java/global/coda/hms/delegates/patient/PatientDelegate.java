package global.coda.hms.delegates.patient;

import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.helpers.patient.PatientHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Patient deligate.
 */
public class PatientDelegate {
    private final PatientHelper patientHelper = new PatientHelper();
    private static final Logger LOGGER = LogManager.getLogger(PatientDelegate.class);


    /**
     * Create patient boolean.
     *
     * @param record the record
     * @return the boolean
     */
    public boolean createPatient(PatientRecord record) {
        LOGGER.info(PatientConstants.CREATE_PATIENT);
        return patientHelper.createPatient(record);
    }

    /**
     * Update patient boolean.
     *
     * @param record the record
     * @return the boolean
     */
    public boolean updatePatient(PatientRecord record) {
        LOGGER.info(PatientConstants.UPDATE_PATIENT);
        return patientHelper.updateUser(record);
    }

    /**
     * Read patient patient record.
     *
     * @param patientName the patient name
     * @return the patient record
     */
    public PatientRecord readPatient(String patientName) {
        LOGGER.info(PatientConstants.READ_PATIENT);
        return patientHelper.readPatient(patientName);
    }

    /**
     * Patient doctors list.
     *
     * @param branchName the branch name
     * @return the list
     */
    public List<DoctorRecord> PatientDoctors(String branchName) {
        LOGGER.info(PatientConstants.PATIENT_DOCTOR);
        return patientHelper.getAllDoctors(branchName);
    }

}
