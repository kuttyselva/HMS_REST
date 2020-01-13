package global.coda.hms.helpers.patient;

import global.coda.hms.applicationconstants.ExceptionConstants;
import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.dao.patient.PatientDao;
import global.coda.hms.exceptionmappers.BusinessException;
import global.coda.hms.exceptionmappers.SystemException;
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
     *
     * @throws SQLException the sql exception
     */
    public PatientHelper() throws SQLException {
    }

    /**
     * Create patient boolean.
     *
     * @param record the record
     * @return the boolean
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    public boolean createPatient(PatientRecord record) throws BusinessException, SystemException {
        LOGGER.info(PatientConstant.CREATE_PATIENT);
        LOGGER.traceEntry(record.toString());
        try {
            if (patientdao.createPatientRecord(record)) {
                return true;
            } else {
                throw new BusinessException(ExceptionConstants.ERR_USER_NOT_CREATE);
            }
        } catch (SQLException e) {
            throw new SystemException(e.getMessage());
        }
    }

    /**
     * Update user boolean.
     *
     * @param record the record
     * @return bool for successful update.
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    public boolean updateUser(PatientRecord record) throws BusinessException, SystemException {
        LOGGER.info(PatientConstant.UPDATE_PATIENT);
        LOGGER.traceEntry(record.toString());
        boolean result;
        // update location
        try {
            result = patientdao.updatePatient(record);
        } catch (SQLException exception) {
            throw new SystemException(exception.getMessage());
        }
        if (!result) {
            throw new BusinessException(ExceptionConstants.ERR_USER_NOT_UPDATE);
        }
        LOGGER.traceExit(record.toString());
        return result;
    }

    /**
     * Read patient patient record.
     *
     * @param patientID the patient id
     * @return record of patient.
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public PatientRecord readPatient(int patientID) throws SystemException, BusinessException {
        LOGGER.info(PatientConstant.READ_PATIENT);
        LOGGER.traceEntry(String.valueOf(patientID));
        PatientRecord patientRecord;
        try {
            patientRecord = patientdao.getPatientRecord(patientID);
        } catch (SQLException exception) {
            throw new SystemException(exception.getMessage());
        }
        if (patientRecord.getName() == null) {
            throw new BusinessException(ExceptionConstants.ERR_USER_NOT_FOUND);
        }
        return patientRecord;

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
     * Delete patient boolean.
     *
     * @param patientID the patient id
     * @return the boolean
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public boolean deletePatient(int patientID) throws SystemException, BusinessException {
        LOGGER.info(PatientConstant.PATIENT_DELETE);
        LOGGER.traceEntry(String.valueOf(patientID));
        try {
            if (patientdao.deletePatient(patientID)) {
                return true;
            } else {
                throw new BusinessException(ExceptionConstants.ERR_USER_NOT_DELETE);
            }
        } catch (SQLException exception) {
            throw new SystemException(exception.getMessage());
        }
    }

    /**
     * Gets all doctors.
     *
     * @param patientID the patient id
     * @return the all doctors
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public List<DoctorRecord> getAllDoctors(int patientID) throws SystemException, BusinessException {
        LOGGER.info(PatientConstant.PATIENT_DOCTOR);
        LOGGER.traceEntry(String.valueOf(patientID));
        try {
            result = patientdao.getAllDoctors(patientID);
        } catch (SQLException e) {
            throw new SystemException(e.getMessage());
        }
        if (result.isEmpty()) {
            throw new BusinessException(ExceptionConstants.ERR_USER_NOT_FOUND);
        }
        LOGGER.traceExit(result);
        return result;
    }

}
