package global.coda.hms.dao.patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import global.coda.hms.applicationconstants.PatientConstants;
import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.config.DatabaseConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Patient dao.
 */
public class PatientDao {
    /**
     * Instantiates a new Patient dao.
     *
     * @throws SQLException the sql exception
     */
    public PatientDao() throws SQLException {
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
     * @throws SQLException the sql exception
     */
    public boolean createPatientRecord(PatientRecord record) throws SQLException {
        LOGGER.traceEntry(record.toString());

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
        } catch (SQLException exception) {
            LOGGER.error(PatientConstants.ERR_PAT_CRT, exception);
            throw exception;
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        } finally {
            connection.close();
        }
        LOGGER.traceExit(result);
        return result > 0;
    }

    /**
     * Gets patient record.
     *
     * @param patientID the patient id
     * @return patient record.
     * @throws SQLException the sql exception
     */
    public PatientRecord getPatientRecord(int patientID) throws SQLException {
        LOGGER.traceEntry(String.valueOf(patientID));
        PatientRecord record = null;
        try {
            record = new PatientRecord();
            PreparedStatement statement = connection
                    .prepareStatement(SQL_QUERIES.getString(PatientConstants.PATIENT_RECORD));
            statement.setInt(PatientConstants.ONE, patientID);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                record.setId(result.getInt(PatientConstants.ONE));
                record.setName(result.getString(PatientConstants.TWO));
                record.setLocation(result.getString(PatientConstants.THREE));
                record.setAge(result.getInt(PatientConstants.FOUR));
                record.setPhone(result.getString(PatientConstants.FIVE));
                record.setDisease(result.getString(PatientConstants.SIX));
            }
            LOGGER.traceExit(record.toString());
            return record;

        } catch (SQLException exception) {
            LOGGER.error(PatientConstants.ERR_PAT_RED, exception);
            throw exception;
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        } finally {
            connection.close();
        }
        LOGGER.traceExit(record);
        return record;
    }

    /**
     * Update patient boolean.
     *
     * @param record of patient.
     * @return bool for success.
     * @throws SQLException the sql exception
     */
    public boolean updatePatient(PatientRecord record) throws SQLException {
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
        } catch (SQLException exception) {
            LOGGER.error(PatientConstants.ERR_PAT_UPD, exception);
            throw exception;
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        } finally {
            connection.close();
        }
        LOGGER.traceExit(result);
        return result > 0;

    }

//    /**
//     * View user details list.
//     *
//     * @param branchName of user.
//     * @return list of doc in branch.
//     * @throws SQLException the sql exception
//     */
//    public List<DoctorRecord> viewUserDetails(String branchName) throws SQLException {
//        LOGGER.traceEntry(branchName);
//        List<DoctorRecord> recordists = new ArrayList<>();
//
//        try {
//            PreparedStatement statement = connection
//                    .prepareStatement(SQL_QUERIES.getString(PatientConstants.DOCTOR_IN_BRANCH));
//            statement.setString(PatientConstants.ONE, branchName);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                DoctorRecord record = new DoctorRecord();
//                record.setName(resultSet.getString(PatientConstants.ONE));
//                record.setSpeciality(resultSet.getString(PatientConstants.TWO));
//                record.setLocation(resultSet.getString(PatientConstants.THREE));
//                record.setPhone(resultSet.getString(PatientConstants.FOUR));
//                recordists.add(record);
//            }
//
//        } catch (SQLException exception) {
//            LOGGER.error(PatientConstants.ERR_PAT_RED, exception);
//            throw exception;
//        } catch (Exception exception) {
//            LOGGER.error(exception.getMessage());
//        } finally {
//            connection.close();
//        }
//        LOGGER.traceExit(recordists.toString());
//        return recordists;
//    }

    /**
     * Gets all doctors.
     *
     * @param patientID the patient id
     * @return the all doctors
     * @throws SQLException the sql exception
     */
    public List<DoctorRecord> getAllDoctors(int patientID) throws SQLException {

        LOGGER.traceEntry(String.valueOf(patientID));
        List<DoctorRecord> recordists = new ArrayList<>();

        try {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_QUERIES.getString(PatientConstants.PATIENT_DOCTORS));
            statement.setInt(PatientConstants.ONE, patientID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DoctorRecord record = new DoctorRecord();
                record.setName(resultSet.getString(PatientConstants.ONE));
                record.setSpeciality(resultSet.getString(PatientConstants.TWO));
                record.setLocation(resultSet.getString(PatientConstants.THREE));
                record.setPhone(resultSet.getString(PatientConstants.FOUR));
                recordists.add(record);
            }
        } catch (SQLException exception) {
            LOGGER.error(PatientConstants.ERR_PAT_DOC, exception);
            throw exception;
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        } finally {
            connection.close();
        }
        LOGGER.traceExit(recordists);
        return recordists;
    }

    /**
     * Delete patient boolean.
     *
     * @param PatientID the patient id
     * @return the boolean
     * @throws SQLException the sql exception
     */
    //FIX
    public boolean deletePatient(int PatientID) throws SQLException {

        LOGGER.traceEntry(String.valueOf(PatientID));
        try {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_QUERIES.getString(PatientConstants.PATIENT_DELETE));
            statement.setInt(PatientConstants.ONE, PatientID);
            int result = statement.executeUpdate();
            if (result < 1) {
                return false;
            }
            LOGGER.traceExit(result);
        } catch (SQLException exception) {
            LOGGER.error(PatientConstants.ERR_PAT_RED, exception);
            throw exception;
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        } finally {
            connection.close();
        }
        return true;
    }
}

