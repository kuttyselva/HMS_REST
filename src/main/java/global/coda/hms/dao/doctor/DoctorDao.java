package global.coda.hms.dao.doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import global.coda.hms.applicationconstants.DoctorConstants;
import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.dao.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static global.coda.hms.dao.patient.PatientDao.SQL_QUERIES;

/**
 * The type Doctor dao.
 */
public class DoctorDao {
    /**
     * The constant QUERIES.
     */
// sql query resource bundle
    public static final ResourceBundle QUERIES = ResourceBundle.getBundle(DoctorConstants.SQLQUEIRES,
            Locale.getDefault());
    /**
     * The constant LOCAL_MESSAGES_BUNDLE.
     */
    private static final Logger LOGGER = LogManager.getLogger(DoctorDao.class);
    // establishing connection between DAO and db
    private Connection connection = DatabaseConnection.createconnection();

    /**
     * Instantiates a new Doctor dao.
     *
     * @throws SQLException the sql exception
     */
    public DoctorDao() throws SQLException {
        super();
    }
    /*
     * creates new Doctor record gets input into doctor bean input doctor record
     * output true for successful insertion
     */

    /**
     * Create doctor record boolean.
     *
     * @param record of doctor.
     * @return true for success.
     * @throws SQLException the sql exception
     */
    public boolean createDoctorRecord(DoctorRecord record) throws SQLException {
        LOGGER.traceEntry(record.toString());
        LOGGER.info(DoctorConstant.CREATE_DOCTOR);
        int result = 0;
        int userid = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    QUERIES.getString(DoctorConstants.DOCTOR_CREATE_USER), Statement.RETURN_GENERATED_KEYS);
            statement.setString(DoctorConstants.ONE, record.getName());
            statement.setString(DoctorConstants.TWO, record.getPassword());
            statement.setString(DoctorConstants.THREE, record.getLocation());
            statement.setInt(DoctorConstants.FOUR, record.getAge());
            statement.setString(DoctorConstants.FIVE, record.getPhone());
            statement.setInt(DoctorConstants.SIX, DoctorConstants.TWO);
            result = statement.executeUpdate();
            ResultSet resultset = statement.getGeneratedKeys();
            if (resultset.next()) {
                userid = resultset.getInt(DoctorConstants.ONE);
            }
            statement = connection.prepareStatement(QUERIES.getString(DoctorConstants.DOCTOR_CREATE_DOCTOR));
            statement.setString(DoctorConstants.ONE, record.getSpeciality());
            statement.setInt(DoctorConstants.TWO, userid);
            result = statement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(DoctorConstant.ERR_DOC_CRT, exception);
            throw exception;
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        LOGGER.traceExit(result);
        return result > 0;
    }

    /*
     * gets Doctor record input doctor name output DoctorRecord for successful
     * Execution
     */

    /**
     * Gets doctor record.
     *
     * @param doctorName of doctor.
     * @return true for success.
     * @throws SQLException the sql exception
     */
    public DoctorRecord getDoctorRecord(String doctorName) throws SQLException {
        LOGGER.traceEntry(doctorName);
        LOGGER.info(DoctorConstant.READ_DOCTOR);
        DoctorRecord record = null;
        try {
            record = new DoctorRecord();
            PreparedStatement statement = connection.prepareStatement(QUERIES.getString(DoctorConstants.DOCTOR_DATA));
            statement.setString(DoctorConstants.ONE, doctorName);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                record.setId(result.getInt(DoctorConstants.ONE));
                record.setName(result.getString(DoctorConstants.TWO));
                record.setLocation(result.getString(DoctorConstants.THREE));
                record.setAge(result.getInt(DoctorConstants.FOUR));
                record.setPhone(result.getString(DoctorConstants.FIVE));
                record.setSpeciality(result.getString(DoctorConstants.SIX));
            }
            return record;

        } catch (SQLException exception) {
            LOGGER.error(DoctorConstant.ERR_DOC_RED, exception);
            throw exception;
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        LOGGER.traceExit(record);
        return record;
    }

    /*
     * updates existing Doctor record gets input into doctor bean input doctor
     * record output true for successful insertion
     */

    /**
     * Update doctor boolean.
     *
     * @param record of doctor.
     * @return true for success.
     * @throws SQLException the sql exception
     */
    public boolean updateDoctor(DoctorRecord record) throws SQLException {
        LOGGER.traceEntry(record.toString());
        LOGGER.info(DoctorConstant.UPDATE_DOCTOR);
        int result = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(QUERIES.getString(DoctorConstants.DOCTOR_UPDATE));
            statement.setString(DoctorConstants.ONE, record.getLocation());
            statement.setInt(DoctorConstants.TWO, record.getAge());
            statement.setString(DoctorConstants.THREE, record.getPhone());
            statement.setInt(DoctorConstants.FOUR, record.getId());
            result = statement.executeUpdate();
            statement = connection.prepareStatement(QUERIES.getString(DoctorConstants.DOCTOR_TABLE_UPDATE));
            statement.setString(DoctorConstants.ONE, record.getSpeciality());
            statement.setInt(DoctorConstants.TWO, record.getId());
            result = statement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(DoctorConstant.ERR_DOC_UPD, exception);
            throw exception;
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        LOGGER.traceExit(result);
        return result > 0;
    }

    /*
     * viewing patients in a branch gets input into doctor bean input branch name
     * output list of patient records
     */

//    /**
//     * View doctor details list.
//     *
//     * @param branchName of doctor.
//     * @return true for success.
//     * @throws SQLException the sql exception
//     */
//    public List<PatientRecord> viewDoctorDetails(String branchName) throws SQLException {
//        LOGGER.traceEntry(branchName);
//        LOGGER.info(DoctorConstant.VIEW_PATIENT);
//        List<PatientRecord> recordlist = new ArrayList<>();
//        try {
//            PreparedStatement statement = connection
//                    .prepareStatement(QUERIES.getString(DoctorConstants.PATIENTS_IN_BRANCH));
//            statement.setString(DoctorConstants.ONE, branchName);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                PatientRecord record = new PatientRecord();
//                record.setName(resultSet.getString(DoctorConstants.ONE));
//                record.setDisease(resultSet.getString(DoctorConstants.TWO));
//                record.setLocation(resultSet.getString(DoctorConstants.THREE));
//                recordlist.add(record);
//            }
//
//        } catch (SQLException exception) {
//            LOGGER.error(DoctorConstant.ERR_DOC_RED, exception);
//            throw exception;
//        } catch (Exception exception) {
//            LOGGER.error(exception.getMessage());
//        }
//        LOGGER.traceExit(recordlist);
//        return recordlist;
//    }

    /**
     * Gets all patients.
     *
     * @param doctorName the doctor name
     * @return the all patients
     * @throws SQLException the sql exception
     */
    public List<PatientRecord> getAllPatients(String doctorName) throws SQLException {
        LOGGER.traceEntry(doctorName);
        LOGGER.info(DoctorConstant.DOCTOR_PATIENT);
        List<PatientRecord> recordists = new ArrayList<>();

        try {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_QUERIES.getString(DoctorConstants.DOCTORS_PATIENT));
            statement.setString(DoctorConstants.ONE, doctorName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PatientRecord record = new PatientRecord();
                record.setName(resultSet.getString(DoctorConstants.ONE));
                record.setDisease(resultSet.getString(DoctorConstants.TWO));
                record.setLocation(resultSet.getString(DoctorConstants.THREE));
                record.setPhone(resultSet.getString(DoctorConstants.FOUR));
                recordists.add(record);
            }

        } catch (SQLException exception) {
            LOGGER.error(DoctorConstant.ERR_DOC_PAT, exception);
            throw exception;
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        LOGGER.traceExit(recordists);
        return recordists;
    }


    /**
     * Read all doctors patients map.
     *
     * @return the map
     * @throws SQLException the sql exception
     */
    public Map<Integer, DoctorRecord> readAllDoctorsPatients() throws SQLException {
        LOGGER.info(DoctorConstant.DOCTORS_PATIENT);
        Map<Integer, DoctorRecord> doctorMap = new HashMap<>();
        PreparedStatement statement = connection.prepareStatement(SQL_QUERIES.getString(DoctorConstants.DOCTORS_PATIENTS));
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            List<PatientRecord> patientList;
            DoctorRecord doctor = new DoctorRecord();
            PatientRecord patient = new PatientRecord();

            doctor.setId(resultSet.getInt(DoctorConstants.ONE));
            patient.setId(resultSet.getInt(DoctorConstants.TWO));
            patient.setName(resultSet.getString(DoctorConstants.FOUR));
            patient.setAge(resultSet.getInt(DoctorConstants.FIVE));
            patient.setDisease(resultSet.getString(DoctorConstants.THREE));
            patient.setLocation(resultSet.getString(DoctorConstants.SIX));
            if (!doctorMap.containsKey(doctor.getId())) {
                patientList = new ArrayList<>();
                patientList.add(patient);
                doctor.setPatientList(patientList);
                doctorMap.put(doctor.getId(), doctor);

            } else {
                doctor = doctorMap.get(doctor.getId());
                patientList = doctor.getPatientList();
                patientList.add(patient);
            }
            LOGGER.trace(doctorMap);
        }


        return doctorMap;
    }
}
