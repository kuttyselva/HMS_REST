package global.coda.hms.delegates.doctor;

import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.helpers.doctor.DoctorConstant;
import global.coda.hms.helpers.doctor.DoctorHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Doctor delegate.
 */
public class DoctorDelegate {
    private final DoctorHelper doctorHelper = new DoctorHelper();
    private static final Logger LOGGER = LogManager.getLogger(DoctorDelegate.class);


    /**
     * Create patient boolean.
     *
     * @param record the record
     * @return the boolean
     */
    public boolean createDoctor(DoctorRecord record) {
        LOGGER.info(DoctorConstant.CREATE_DOCTOR);
        return doctorHelper.createDoctor(record);
    }

    /**
     * Update patient boolean.
     *
     * @param record the record
     * @return the boolean
     */
    public boolean updateDoctor(DoctorRecord record) {
        LOGGER.info(DoctorConstant.UPDATE_DOCTOR);
        return doctorHelper.updateDoctor(record);
    }

    /**
     * Read patient patient record.
     *
     * @param doctorName the patient name
     * @return the patient record
     */
    public DoctorRecord readDoctor(String doctorName) {
        LOGGER.info(DoctorConstant.READ_DOCTOR);
        return doctorHelper.readDoctor(doctorName);
    }

    /**
     * Patient doctors list.
     *
     * @param branchName the branch name
     * @return the list
     */
    public List<PatientRecord> doctorBranch(String branchName) {
        LOGGER.info(DoctorConstant.PATIENT_DOCTOR);
        return doctorHelper.doctorBranch(branchName);
    }

    /**
     * Get all patients list.
     *
     * @param doctorName the doctor name
     * @return the list
     */
    public List<PatientRecord> getAllPatients(String doctorName) {
        LOGGER.info(DoctorConstant.PATIENT_DOCTOR);
        return doctorHelper.getAllPatients(doctorName);
    }


}
