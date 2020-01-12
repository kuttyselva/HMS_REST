package global.coda.hms.delegates.doctor;

import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.delegates.patient.PatientConstants;
import global.coda.hms.exceptionmappers.BusinessException;
import global.coda.hms.exceptionmappers.SystemException;
import global.coda.hms.helpers.doctor.DoctorHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * The type Doctor delegate.
 */
public class DoctorDelegate {
    private final DoctorHelper doctorHelper = new DoctorHelper();
    private static final Logger LOGGER = LogManager.getLogger(DoctorDelegate.class);

    /**
     * Instantiates a new Doctor delegate.
     *
     * @throws SQLException the sql exception
     */
    public DoctorDelegate() throws SQLException {
    }


    /**
     * Create patient boolean.
     *
     * @param record the record
     * @return the boolean
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public boolean createDoctor(DoctorRecord record) throws  SystemException, BusinessException {
        if (record == null || record.isEmpty()) {
            throw new BusinessException();
        }
        LOGGER.info(DoctorConstants.CREATE_DOCTOR);
        try {
            return doctorHelper.createDoctor(record);
        } catch (SQLException exception) {
            throw new SystemException();
        }
    }

    /**
     * Update patient boolean.
     *
     * @param record the record
     * @return the boolean
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    public boolean updateDoctor(DoctorRecord record) throws BusinessException, SystemException {
        if (record == null || record.isEmpty()) {
            throw new BusinessException();
        }
        LOGGER.info(DoctorConstants.CREATE_DOCTOR);
        try {
            return doctorHelper.updateDoctor(record);
        } catch (SQLException exception) {
            throw new SystemException();
        }
    }

    /**
     * Read patient patient record.
     *
     * @param doctorName the patient name
     * @return the patient record
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    public DoctorRecord readDoctor(String doctorName) throws BusinessException, SystemException {
        if (doctorName.length() < 2) {
            throw new BusinessException();
        }
        LOGGER.info(DoctorConstants.READ_DOCTOR);
        try {
            return doctorHelper.readDoctor(doctorName);
        } catch (SQLException e) {
            throw new SystemException();
        }
    }

//    /**
//     * Patient doctors list.
//     *
//     * @param branchName the branch name
//     * @return the list
//     */
//    public List<PatientRecord> doctorBranch(String branchName) {
//        LOGGER.traceEntry(branchName);
//        LOGGER.info(DoctorConstants.PATIENT_DOCTOR);
//        List<PatientRecord> patientRecordList = doctorHelper.doctorBranch(branchName);
//        LOGGER.traceExit(patientRecordList);
//        return patientRecordList;
//    }

    /**
     * Get all patients list.
     *
     * @param doctorName the doctor name
     * @return the list
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    public List<PatientRecord> getAllPatients(String doctorName) throws BusinessException, SystemException {
        LOGGER.info(PatientConstants.PATIENT_DOCTOR);
        if (doctorName.length() < 1) {
            throw new BusinessException();
        }
        try {
            return doctorHelper.getAllPatients(doctorName);
        } catch (SQLException e) {
            throw new SystemException();
        }
    }


}
