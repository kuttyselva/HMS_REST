package global.coda.hms.delegates.doctor;

import global.coda.hms.applicationconstants.ExceptionConstants;
import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.exceptionmappers.BusinessException;
import global.coda.hms.exceptionmappers.SystemException;
import global.coda.hms.helpers.doctor.DoctorHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public boolean createDoctor(DoctorRecord record) throws SystemException, BusinessException {
        if (doctorHelper.createDoctor(record)) {
            return true;
        } else {
            throw new BusinessException(ExceptionConstants.ERR_USER_NOT_CREATE);
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
        try {
            if (doctorHelper.updateDoctor(record)) {
                return true;
            } else {
                throw new BusinessException(ExceptionConstants.ERR_USER_NOT_UPDATE);
            }
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
        return doctorHelper.readDoctor(doctorName);
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
        LOGGER.traceEntry(doctorName);
        if (doctorName.length() < 1) {
            throw new BusinessException(ExceptionConstants.ERR_USER_NOT_FOUND);
        }
        return doctorHelper.getAllPatients(doctorName);
    }

    /**
     * Read all doctors patients delegate list.
     *
     * @return the list
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    public List<DoctorRecord> readAllDoctorsPatientsDelegate() throws BusinessException, SystemException {

        Map<Integer, DoctorRecord> doctorMap;
        doctorMap = doctorHelper.readAllDoctorsPatientsHelper();
        if (doctorMap.isEmpty()) {
            throw new BusinessException(ExceptionConstants.ERR_USER_NOT_FOUND);
        }
        List<DoctorRecord> doctorList = new ArrayList<>();
        for (Map.Entry<Integer, DoctorRecord> doctor : doctorMap.entrySet()) {

            doctorList.add(doctor.getValue());

        }


        return doctorList;
    }


    /**
     * Delete patient boolean.
     *
     * @param doctorID the doctor id
     * @return the boolean
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public boolean deleteDoctor(int doctorID) throws SystemException, BusinessException {
        LOGGER.traceEntry(String.valueOf(doctorID));
        if (doctorID == -1) {
            throw new BusinessException(ExceptionConstants.ERR_USER_NOT_FOUND);
        }
        boolean result = doctorHelper.deleteDoctor(doctorID);
        LOGGER.traceExit(result);
        return result;
    }


}
