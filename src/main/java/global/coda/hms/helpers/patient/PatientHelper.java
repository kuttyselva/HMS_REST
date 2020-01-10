package global.coda.hms.helpers.patient;

import global.coda.hms.applicationconstants.PatientConstants;
import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.dao.patient.PatientDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The type Patient helper.
 */
public class PatientHelper {
    // Logger class will log the status
    private static final Logger LOGGER = LogManager.getLogger(PatientHelper.class);
    /**
     * The constant LOCAL_MESSAGES_BUNDLE.
     */
// resource bundle initialization
    public static final ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle(PatientConstants.PATIENT,
            Locale.getDefault());
    private PatientDao patientdao = new PatientDao();

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
     */
    public boolean createPatient(PatientRecord record) {
        if (patientdao.createPatientRecord(record)) {
            return true;
        }
        return false;
    }

    /**
     * Update user boolean.
     *
     * @param record the record
     * @return bool for successful update.
     */

    public boolean updateUser(PatientRecord record) {
        boolean result = false;
        // update location

        result = new PatientDao().updatePatient(record);

        return result;
    }

    /**
     * Read patient patient record.
     *
     * @param patientName to get data.
     * @return record of patient.
     */
    public PatientRecord readPatient(String patientName) {
        return patientdao.getPatientRecord(patientName);
    }

    /**
     * View users list.
     *
     * @param branchname to store name of branch.
     * @return list for successful exe.
     */
    public List<DoctorRecord> viewUsers(String branchname) {
        List<DoctorRecord> result = null;
        result = new PatientDao().viewUserDetails(branchname);
        return result;
    }

}