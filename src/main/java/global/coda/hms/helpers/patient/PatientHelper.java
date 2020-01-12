package global.coda.hms.helpers.patient;

import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.dao.patient.PatientDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
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
     * @throws SQLException the sql exception
     */
    public boolean createPatient(PatientRecord record) throws SQLException {
        LOGGER.info(PatientConstant.CREATE_PATIENT);
        LOGGER.traceEntry(record.toString());
        return patientdao.createPatientRecord(record);
    }

    /**
     * Update user boolean.
     *
     * @param record the record
     * @return bool for successful update.
     * @throws SQLException the sql exception
     */
    public boolean updateUser(PatientRecord record) throws SQLException {
        LOGGER.info(PatientConstant.UPDATE_PATIENT);
        LOGGER.traceEntry(record.toString());
        boolean result;
        // update location
        result = patientdao.updatePatient(record);
        LOGGER.traceExit(record.toString());
        return result;
    }

    /**
     * Read patient patient record.
     *
     * @param patientName to get data.
     * @return record of patient.
     * @throws SQLException the sql exception
     */
    public PatientRecord readPatient(String patientName) throws SQLException {
        LOGGER.info(PatientConstant.READ_PATIENT);
        LOGGER.traceEntry(patientName);
        return patientdao.getPatientRecord(patientName);
    }

    /**
     * View users list.
     *
     * @param branchName to store name of branch.
     * @return list for successful exe.
     * @throws SQLException the sql exception
     */
    public List<DoctorRecord> viewPatients(String branchName) throws SQLException {
        LOGGER.info(PatientConstant.READ_PATIENT);
        LOGGER.traceEntry(branchName);
        result = new PatientDao().viewUserDetails(branchName);
        LOGGER.traceExit(result);
        return result;
    }


    /**
     * Gets all doctors.
     *
     * @param patientName the patient name
     * @return the all doctors
     * @throws SQLException the sql exception
     */
    public List<DoctorRecord> getAllDoctors(String patientName) throws SQLException {
        LOGGER.info(PatientConstant.PATIENT_DOCTOR);
        LOGGER.traceEntry(patientName);
        result = patientdao.getAllDoctors(patientName);
        LOGGER.traceExit(result);
        return result;
    }

}
