package global.coda.hms.helpers.doctor;

import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.dao.doctor.DoctorDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

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
     * @throws SQLException the sql exception
     */
    public boolean createDoctor(DoctorRecord record) throws SQLException {
        LOGGER.traceEntry(record.toString());
        LOGGER.info(DoctorConstant.CREATE_DOCTOR);
        boolean result = doctorDao.createDoctorRecord(record);
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
        LOGGER.info(DoctorConstant.UPDATE_DOCTOR);
        boolean result = doctorDao.updateDoctor(record);
        LOGGER.traceExit(result);
        return result;
    }

    /**
     * Read doctor doctor record.
     *
     * @param doctorName the doctor name
     * @return the doctor record
     * @throws SQLException the sql exception
     */
    public DoctorRecord readDoctor(String doctorName) throws SQLException {
        LOGGER.traceEntry(doctorName);
        LOGGER.info(DoctorConstant.READ_DOCTOR);
        DoctorRecord result = doctorDao.getDoctorRecord(doctorName);
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
     * @throws SQLException the sql exception
     */
    public List<PatientRecord> getAllPatients(String doctorName) throws SQLException {
        LOGGER.traceEntry(doctorName);
        LOGGER.info(DoctorConstant.PATIENT_DOCTOR);
        List<PatientRecord> result = doctorDao.getAllPatients(doctorName);
        LOGGER.traceExit(result);
        return result;
    }

}
