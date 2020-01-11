package global.coda.hms.helpers.doctor;

import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.dao.doctor.DoctorDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Doctor helper.
 */
public class DoctorHelper {
    private static final Logger LOGGER = LogManager.getLogger(DoctorHelper.class);
    private DoctorDao doctorDao = new DoctorDao();
    private List<PatientRecord> result = null;

    /**
     * Instantiates a new Doctor helper.
     */
    public DoctorHelper() {

    }

    /**
     * Create doctor boolean.
     *
     * @param record the record
     * @return the boolean
     */
    public boolean createDoctor(DoctorRecord record) {
        LOGGER.info(DoctorConstant.CREATE_DOCTOR);
        return doctorDao.createDoctorRecord(record);
    }

    /**
     * Update doctor boolean.
     *
     * @param record the record
     * @return the boolean
     */
    public boolean updateDoctor(DoctorRecord record) {
        LOGGER.info(DoctorConstant.UPDATE_DOCTOR);
        boolean result;
        // update location
        result = new DoctorDao().updateDoctor(record);
        return result;
    }

    /**
     * Read doctor doctor record.
     *
     * @param doctorName the doctor name
     * @return the doctor record
     */
    public DoctorRecord readDoctor(String doctorName) {
        LOGGER.info(DoctorConstant.READ_DOCTOR);
        return doctorDao.getDoctorRecord(doctorName);
    }

    /**
     * View users list.
     *
     * @param branchName the branch name
     * @return the list
     */
    public List<PatientRecord> doctorBranch(String branchName) {
        LOGGER.info(DoctorConstant.PATIENT_DOCTOR);
        result = doctorDao.viewDoctorDetails(branchName);
        return result;
    }

    /**
     * Get all patients list.
     *
     * @param doctorName the doctor name
     * @return the list
     */
    public List<PatientRecord> getAllPatients(String doctorName) {
        LOGGER.info(DoctorConstant.PATIENT_DOCTOR);
        result = doctorDao.getAllPatients(doctorName);
        return result;
    }

}
