package global.coda.hms.delegates.patient;

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


    /**
     * Create patient boolean.
     *
     * @param record the record
     * @return the boolean
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public boolean createPatient(PatientRecord record) throws SystemException, BusinessException {
        if (record == null || record.isEmpty()) {
            throw new BusinessException();
        }
        LOGGER.info(PatientConstants.CREATE_PATIENT);
        try {
            return patientHelper.createPatient(record);
        } catch (SQLException exception) {
            throw new SystemException();
        }
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
        if (record == null || record.isEmpty()) {
            throw new BusinessException();
        }
        LOGGER.info(PatientConstants.UPDATE_PATIENT);
        try {
            return patientHelper.updateUser(record);
        } catch (SQLException e) {
            throw new SystemException();
        }
    }

    /**
     * Read patient patient record.
     *
     * @param patientName the patient name
     * @return the patient record
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public PatientRecord readPatient(String patientName) throws SystemException, BusinessException {
        if (patientName.length() < 2) {
             throw new BusinessException();
        }
        LOGGER.info(PatientConstants.READ_PATIENT);
        try {
            return patientHelper.readPatient(patientName);
        } catch (SQLException e) {
            throw new SystemException();
        }
    }

    /**
     * Patient doctors list.
     *
     * @param patientName the patient name
     * @return the list
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public List<DoctorRecord> PatientDoctors(String patientName) throws SystemException, BusinessException {
        LOGGER.info(PatientConstants.PATIENT_DOCTOR);
        if (patientName.length() < 1) {
            throw new BusinessException();
        }
        try {
            return patientHelper.getAllDoctors(patientName);
        } catch (SQLException e) {
            throw new SystemException();
        }
    }
}
