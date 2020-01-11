package global.coda.hms.dao.patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import global.coda.hms.applicationconstants.PatientConstants;
import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.dao.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Patient dao.
 */
public class PatientDao {
    /**
     * Instantiates a new Patient dao.
     */
    public PatientDao() {
        super();
        // TODO Auto-generated constructor stub
    }

    private Connection connection = DatabaseConnection.createconnection();
    /**
     * The constant SQL_QUERIES.
     */
    public static final ResourceBundle SQL_QUERIES = ResourceBundle.getBundle(PatientConstants.SQLQUEIRES,
            Locale.getDefault());
    private static final Logger LOGGER = LogManager.getLogger(PatientDao.class);

    /**
     * Create patient record boolean.
     *
     * @param record of patient.
     * @return bool for success.
     */
    public boolean createPatientRecord(PatientRecord record) {
        LOGGER.traceEntry(record.toString());
        LOGGER.info(PatientConstant.CREATE_PATIENT);
        int result = 0;
        int userid = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    SQL_QUERIES.getString(PatientConstants.PATIENT_CREATE_USER), Statement.RETURN_GENERATED_KEYS);
            statement.setString(PatientConstants.ONE, record.getName());
            statement.setString(PatientConstants.TWO, record.getPassword());
            statement.setString(PatientConstants.THREE, record.getLocation());
            statement.setInt(PatientConstants.FOUR, record.getAge());
            statement.setString(PatientConstants.FIVE, record.getPhone());
            statement.setInt(PatientConstants.SIX, PatientConstants.ONE);
            result = statement.executeUpdate();
            ResultSet resultset = statement.getGeneratedKeys();
            if (resultset.next()) {
                userid = resultset.getInt(PatientConstants.ONE);
            }
            statement = connection.prepareStatement(SQL_QUERIES.getString(PatientConstants.PATIENT_CREATE_PATIENT));
            statement.setString(PatientConstants.ONE, record.getDisease());
            statement.setInt(PatientConstants.TWO, userid);
            result = statement.executeUpdate();
            LOGGER.traceExit();
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return result > 0;
    }

    /**
     * Gets patient record.
     *
     * @param patientName of user.
     * @return patient record.
     */
    public PatientRecord getPatientRecord(String patientName) {
        LOGGER.info(PatientConstant.READ_PATIENT);
        LOGGER.traceEntry(patientName);
        PatientRecord record = null;
        try {
            record = new PatientRecord();
            PreparedStatement statement = connection
                    .prepareStatement(SQL_QUERIES.getString(PatientConstants.PATIENT_RECORD));
            statement.setString(PatientConstants.ONE, patientName);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                record.setId(result.getInt(PatientConstants.ONE));
                record.setName(result.getString(PatientConstants.TWO));
                record.setLocation(result.getString(PatientConstants.THREE));
                record.setAge(result.getInt(PatientConstants.FOUR));
                record.setPhone(result.getString(PatientConstants.FIVE));
                record.setDisease(result.getString(PatientConstants.SIX));
            }
            LOGGER.traceEntry(record.toString());
            return record;

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        assert record != null;
        LOGGER.traceEntry(record.toString());
        return record;
    }

    /**
     * Update patient boolean.
     *
     * @param record of patient.
     * @return bool for success.
     */
    public boolean updatePatient(PatientRecord record) {
        LOGGER.info(PatientConstant.UPDATE_PATIENT);
        LOGGER.traceEntry(record.toString());
        int result = 0;
        try {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_QUERIES.getString(PatientConstants.PATIENT_UPDATE));
            statement.setString(PatientConstants.ONE, record.getLocation());
            statement.setInt(PatientConstants.TWO, record.getAge());
            statement.setString(PatientConstants.THREE, record.getPhone());
            statement.setInt(PatientConstants.FOUR, record.getId());
            result = statement.executeUpdate();
            statement = connection.prepareStatement(SQL_QUERIES.getString(PatientConstants.PATIENT_TABLE_UPDATE));
            statement.setString(PatientConstants.ONE, record.getDisease());
            statement.setInt(PatientConstants.TWO, record.getId());
            result = statement.executeUpdate();
            LOGGER.traceExit(result);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return result > 0;

    }

    /**
     * View user details list.
     *
     * @param branchName of user.
     * @return list of doc in branch.
     */
    public List<DoctorRecord> viewUserDetails(String branchName) {
        LOGGER.info(PatientConstant.VIEW_DOCTOR);
        LOGGER.traceEntry(branchName);
        List<DoctorRecord> recordists = new ArrayList<>();

        try {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_QUERIES.getString(PatientConstants.DOCTOR_IN_BRANCH));
            statement.setString(PatientConstants.ONE, branchName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DoctorRecord record = new DoctorRecord();
                record.setName(resultSet.getString(PatientConstants.ONE));
                record.setSpeciality(resultSet.getString(PatientConstants.TWO));
                record.setLocation(resultSet.getString(PatientConstants.THREE));
                record.setPhone(resultSet.getString(PatientConstants.FOUR));
                recordists.add(record);
            }

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        LOGGER.traceExit(recordists.toString());
        return recordists;
    }

    /**
     * Gets all doctors.
     *
     * @param patientName the patient name
     * @return the all doctors
     */
    public List<DoctorRecord> getAllDoctors(String patientName) {
        LOGGER.info(PatientConstant.PATIENT_DOCTOR);
        LOGGER.traceEntry(patientName);
        List<DoctorRecord> recordists = new ArrayList<>();

        try {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_QUERIES.getString(PatientConstants.PATIENT_DOCTORS));
            statement.setString(PatientConstants.ONE, patientName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DoctorRecord record = new DoctorRecord();
                record.setName(resultSet.getString(PatientConstants.ONE));
                record.setSpeciality(resultSet.getString(PatientConstants.TWO));
                record.setLocation(resultSet.getString(PatientConstants.THREE));
                record.setPhone(resultSet.getString(PatientConstants.FOUR));

                recordists.add(record);
            }

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        LOGGER.traceExit(recordists);
        return recordists;
    }

}

