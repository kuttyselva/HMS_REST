package global.coda.hms.delegates.patient;

import global.coda.hms.applicationconstants.ExceptionConstants;
import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.exceptionmappers.BusinessException;
import global.coda.hms.exceptionmappers.SystemException;
import global.coda.hms.helpers.patient.PatientHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * The type Patient deligate.
 */
public class PatientDelegate {
    private final PatientHelper patientHelper = new PatientHelper();
    private static final Logger LOGGER = LogManager.getLogger(PatientDelegate.class);
    private static boolean result = false;

    /**
     * Instantiates a new Patient delegate.
     *
     * @throws SQLException the sql exception
     */
    public PatientDelegate() throws SQLException {
    }


    /**
     * Create patient boolean.
     *
     * @param record the record
     * @return the boolean
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public boolean createPatient(PatientRecord record) throws SystemException, BusinessException {
        LOGGER.traceEntry(record.toString());
        result = patientHelper.createPatient(record);
        return result;
    }

    /**
     * Update patient boolean.
     *
     * @param record the record
     * @return the boolean
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public boolean updatePatient(PatientRecord record) throws SystemException, BusinessException {
        LOGGER.traceEntry(record.toString());
        if (patientHelper.updateUser(record)) {
            result = true;
        } else {
            throw new BusinessException(ExceptionConstants.ERR_USER_NOT_DELETE);
        }
        LOGGER.traceExit(true);
        return result;
    }

    /**
     * Read patient patient record.
     *
     * @param patientID the patient id
     * @return the patient record
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public PatientRecord readPatient(int patientID) throws SystemException, BusinessException {
        LOGGER.traceEntry(String.valueOf(patientID));
        if (patientID == -1) {
            throw new BusinessException(ExceptionConstants.ERR_USER_NOT_FOUND);
        }
        PatientRecord patientRecord = patientHelper.readPatient(patientID);
        LOGGER.traceExit(patientRecord);
        return patientRecord;
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
        LOGGER.traceEntry(String.valueOf(patientID));
        if (patientID == -1) {
            throw new BusinessException(ExceptionConstants.ERR_USER_NOT_FOUND);
        }
        result = patientHelper.deletePatient(patientID);
        LOGGER.traceExit(result);
        return result;
    }

    /**
     * Patient doctors list.
     *
     * @param patientID the patient id
     * @return the list
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public List<DoctorRecord> PatientDoctors(int patientID) throws SystemException, BusinessException {
        LOGGER.traceEntry(String.valueOf(patientID));
        if (patientID == -1) {
            throw new BusinessException(ExceptionConstants.ERR_USER_NOT_FOUND);
        }
        LOGGER.traceExit(patientID);
        return patientHelper.getAllDoctors(patientID);
    }
}
