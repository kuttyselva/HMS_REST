package global.coda.hms.dao;

import global.coda.hms.bean.PatientRecord;
import global.coda.hms.constants.DaoTestConstants;
import global.coda.hms.dao.patient.PatientDao;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * The type Patient dao test.
 */
public class PatientDaoTest {

    /**
     * to read patient by id.
     *
     * @throws SQLException thrown in case of sql errors
     */
    @Test
    public void readPatient() throws SQLException {
        PatientDao patientDao = new PatientDao();
        PatientRecord user = DaoTestConstants.getPatient();
        PatientRecord patient = patientDao.getPatientRecord("selva");
        assertEquals(user.toString(), patient.toString());
    }

    /**
     * Create patient.
     *
     * @throws SQLException the sql exception
     */
    @Test
    public void createPatient() throws SQLException {
        PatientDao patientDao = new PatientDao();
        PatientRecord record=DaoTestConstants.createPatient();
        record.setPassword("1234");
        assertTrue(patientDao.createPatientRecord(record));
    }

    @Test
    public void updatePatient() throws SQLException{
        PatientDao patientDao=new PatientDao();
        assertTrue(patientDao.updatePatient(DaoTestConstants.getPatient()));
    }

    @Test
    public void patientDoctor() throws SQLException{
        PatientDao patientDao=new PatientDao();
        assertEquals(patientDao.getAllDoctors(DaoTestConstants.createPatient().getName()),new ArrayList<>());
    }
}
