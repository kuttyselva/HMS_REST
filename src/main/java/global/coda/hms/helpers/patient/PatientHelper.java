package global.coda.hms.helpers.patient;

import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.dao.patient.PatientDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


/**
 * The type Patient helper.
 */
public class PatientHelper {
    // Logger class will log the status
    private static final Logger LOGGER = LogManager.getLogger(PatientHelper.class);
    /**
     * The constant LOCAL_MESSAGES_BUNDLE.
     */
    private PatientDao patientdao = new PatientDao();
    private List<DoctorRecord> result = null;

    /**
     * PatientService constructor.
     */
    public PatientHelper() {
    }

    /**
     * Create patient boolean.
     *
     * @param record the record
     * @return the boolean
     */
    public boolean createPatient(PatientRecord record) {
        LOGGER.info(PatientConstant.CREATE_PATIENT);
        return patientdao.createPatientRecord(record);
    }

    /**
     * Update user boolean.
     *
     * @param record the record
     * @return bool for successful update.
     */
    public boolean updateUser(PatientRecord record) {
        LOGGER.info(PatientConstant.UPDATE_PATIENT);
        boolean result = false;
        // update location
        result = patientdao.updatePatient(record);
        return result;
    }

    /**
     * Read patient patient record.
     *
     * @param patientName to get data.
     * @return record of patient.
     */
    public PatientRecord readPatient(String patientName) {
        LOGGER.info(PatientConstant.READ_PATIENT);
        return patientdao.getPatientRecord(patientName);
    }

    /**
     * View users list.
     *
     * @param branchName to store name of branch.
     * @return list for successful exe.
     */
    public List<DoctorRecord> viewPatients(String branchName) {
        LOGGER.info(PatientConstant.READ_PATIENT);
        result = new PatientDao().viewUserDetails(branchName);
        return result;
    }

    /**
     * Gets all doctors.
     *
     * @param patientName the patient name
     * @return the all doctors
     */
    public List<DoctorRecord> getAllDoctors(String patientName) {
        LOGGER.info(PatientConstant.PATIENT_DOCTOR);
        result = patientdao.getAllDoctors(patientName);
        return result;
    }

}
