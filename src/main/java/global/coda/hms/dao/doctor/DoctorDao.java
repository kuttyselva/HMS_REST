package global.coda.hms.dao.doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import global.coda.hms.applicationconstants.DoctorConstants;
import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.dao.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
// local message resource bundle
    public static final ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle(DoctorConstants.DOCTOR,
            Locale.getDefault());

    private static final Logger LOGGER = LogManager.getLogger(DoctorDao.class);
    // establishing connection between DAO and db
    private Connection connection = DatabaseConnection.createconnection();
    /*
     * creates new Doctor record gets input into doctor bean input doctor record
     * output true for successful insertion
     */

    /**
     * Create doctor record boolean.
     *
     * @param record of doctor.
     * @return true for success.
     */
    public boolean createDoctorRecord(DoctorRecord record) {

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
                userid = resultset.getInt(1);
            }
            statement = connection.prepareStatement(QUERIES.getString(DoctorConstants.DOCTOR_CREATE_DOCTOR));
            statement.setString(1, record.getSpeciality());
            statement.setInt(2, userid);
            result = statement.executeUpdate();
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return result > 0;
    }

    /*
     * gets Doctor record input doctor name output DoctorRecord for successful
     * Execution
     */

    /**
     * Gets doctor record.
     *
     * @param patientName of doctor.
     * @return true for success.
     */
    public DoctorRecord getDoctorRecord(String patientName) {
        DoctorRecord record = null;
        try {
            record = new DoctorRecord();
            PreparedStatement statement = connection.prepareStatement(QUERIES.getString(DoctorConstants.DOCTOR_DATA));
            statement.setString(DoctorConstants.ONE, patientName);
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

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
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
     */
    public boolean updateDoctor(DoctorRecord record) {

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
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return result > 0;
    }

    /*
     * viewing patients in a branch gets input into doctor bean input branch name
     * output list of patient records
     */

    /**
     * View doctor details list.
     *
     * @param branchname of doctor.
     * @return true for success.
     */
    public List<PatientRecord> viewDoctorDetails(String branchname) {
        List<PatientRecord> recordlist = new ArrayList<>();
        try {
            PreparedStatement statement = connection
                    .prepareStatement(QUERIES.getString(DoctorConstants.PATIENTS_IN_BRANCH));
            statement.setString(1, branchname);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PatientRecord record = new PatientRecord();
                record.setName(resultSet.getString(DoctorConstants.ONE));
                record.setDisease(resultSet.getString(DoctorConstants.TWO));
                record.setLocation(resultSet.getString(DoctorConstants.THREE));
                recordlist.add(record);
            }

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return recordlist;
    }

}

