package global.coda.hms.helpers.doctor;

import global.coda.hms.applicationconstants.ExceptionConstants;
import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.dao.doctor.DoctorDao;
import global.coda.hms.exceptionmappers.BusinessException;
import global.coda.hms.exceptionmappers.SystemException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * The type Doctor helper.
 */
public class DoctorHelper {
    private static final Logger LOGGER = LogManager.getLogger(DoctorHelper.class);
    private DoctorDao doctorDao = new DoctorDao();

    /**
     * Instantiates a new Doctor helper.
     *
     * @throws SQLException the sql exception
     */
    public DoctorHelper() throws SQLException {

    }

    /**
     * Create doctor boolean.
     *
     * @param record the record
     * @return the boolean
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    public boolean createDoctor(DoctorRecord record) throws BusinessException, SystemException {
        LOGGER.traceEntry(record.toString());
        boolean result;
        try {
            result = doctorDao.createDoctorRecord(record);
            if (!result) {
                throw new BusinessException(ExceptionConstants.ERR_USER_NOT_CREATE);
            }
        } catch (SQLException e) {
            throw new SystemException(e.getMessage());
        }
        LOGGER.traceExit(result);
        return result;
    }

    /**
     * Update doctor boolean.
     *
     * @param record the record
     * @return the boolean
     * @throws SQLException the sql exception
     */
    public boolean updateDoctor(DoctorRecord record) throws SQLException {
        LOGGER.traceEntry(record.toString());
        boolean result = doctorDao.updateDoctor(record);
        LOGGER.traceExit(result);
        return result;
    }

    /**
     * Read doctor doctor record.
     *
     * @param doctorName the doctor name
     * @return the doctor record
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    public DoctorRecord readDoctor(String doctorName) throws BusinessException, SystemException {
        LOGGER.traceEntry(doctorName);
        DoctorRecord result;
        try {
            result = doctorDao.getDoctorRecord(doctorName);
            if (result.getName() == null) {
                throw new BusinessException(ExceptionConstants.ERR_USER_NOT_FOUND);
            }
        } catch (SQLException e) {
            throw new SystemException(e.getMessage());
        }
        LOGGER.traceExit(result);
        return result;
    }

//    /**
//     * View users list.
//     *
//     * @param branchName the branch name
//     * @return the list
//     */
//    public List<PatientRecord> doctorBranch(String branchName) {
//        LOGGER.info(DoctorConstant.PATIENT_DOCTOR);
//        result = doctorDao.viewDoctorDetails(branchName);
//        return result;
//    }

    /**
     * Get all patients list.
     *
     * @param doctorName the doctor name
     * @return the list
     * @throws SystemException the system exception
     */
    public List<PatientRecord> getAllPatients(String doctorName) throws SystemException {
        LOGGER.traceEntry(doctorName);
        List<PatientRecord> result;
        try {
            result = doctorDao.getAllPatients(doctorName);
            if (result.isEmpty()) {
                throw new SystemException(ExceptionConstants.ERR_USER_NOT_FOUND);
            }
        } catch (SQLException e) {
            throw new SystemException(e.getMessage());
        }
        LOGGER.traceExit(result);
        return result;
    }

    /**
     * Read all doctors patients helper map.
     *
     * @return the map
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public Map<Integer, DoctorRecord> readAllDoctorsPatientsHelper() throws SystemException, BusinessException {
        Map<Integer, DoctorRecord> doctorMap;
        try {
            doctorMap = doctorDao.readAllDoctorsPatients();
            if (doctorMap.get(1).getId() == -1) {
                throw new BusinessException();
            }
        } catch (SQLException e) {
            throw new SystemException(e.getMessage());
        }
        return doctorMap;
    }

    /**
     * Delete doctor boolean.
     *
     * @param doctorID the doctor id
     * @return the boolean
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public boolean deleteDoctor(int doctorID) throws SystemException, BusinessException {
        LOGGER.traceEntry(String.valueOf(doctorID));
        try {
            if (doctorDao.deleteDoctor(doctorID)) {
                return true;
            } else {
                throw new BusinessException(ExceptionConstants.ERR_USER_NOT_DELETE);
            }
        } catch (SQLException exception) {
            throw new SystemException(exception.getMessage());
        }
    }

}
