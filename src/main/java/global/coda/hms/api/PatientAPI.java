package global.coda.hms.api;

import global.coda.hms.applicationconstants.ResponseStatus;
import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.delegates.patient.PatientDelegate;
import global.coda.hms.exceptionmappers.BusinessException;
import global.coda.hms.exceptionmappers.SystemException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * The type Patient api.
 */
@Path("/patient")
public class PatientAPI {
    private JSONObject jsonobject = new JSONObject();
    private static final Logger LOGGER = LogManager.getLogger(PatientAPI.class);
    private PatientDelegate patientDelegate = new PatientDelegate();
    private PatientRecord record = new PatientRecord();

    /**
     * Instantiates a new Patient api.
     *
     * @throws SQLException the sql exception
     */
    public PatientAPI() throws SQLException {
    }

    /**
     * CreatePatient json object.
     *
     * @param name     the name
     * @param age      the age
     * @param location the location
     * @param phone    the phone
     * @param disease  the disease
     * @param password the password
     * @return the json object
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject createPatient(@FormParam("name") String name, @FormParam("age") int age,
                                    @FormParam("location") String location, @FormParam("phone") String phone,
                                    @FormParam("disease") String disease, @FormParam("password") String password) throws SystemException, BusinessException {
        LOGGER.traceEntry(name, age, location, phone, disease);
        String message;
        record.setAge(age);
        record.setDisease(disease);
        record.setName(name);
        record.setPhone(phone);
        record.setPassword(password);
        record.setLocation(location);
        if (patientDelegate.createPatient(record)) {
            message = "created successfully";
            jsonobject.put("status", ResponseStatus.OK);
        } else {
            message = "try again";
            jsonobject.put("status", ResponseStatus.BAD_REQUEST);
        }
        jsonobject.put("message", message);
        LOGGER.traceExit(jsonobject);
        return jsonobject;

    }

    /**
     * Edit patient json object.
     *
     * @param name     the name
     * @param age      the age
     * @param location the location
     * @param phone    the phone
     * @param disease  the disease
     * @param password the password
     * @param id       the id
     * @return the json object
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject editPatient(@FormParam("name") String name, @FormParam("age") int age,
                                  @FormParam("location") String location, @FormParam("phone") String phone,
                                  @FormParam("disease") String disease, @FormParam("password") String password, @FormParam("id") int id) throws SystemException, BusinessException {
        LOGGER.traceEntry(name, age, location, phone, disease);
        String message;
        record.setAge(age);
        record.setDisease(disease);
        record.setName(name);
        record.setPhone(phone);
        record.setPassword(password);
        record.setLocation(location);
        record.setId(id);
        if (patientDelegate.updatePatient(record)) {
            message = "updated successfully";
            jsonobject.put("status", ResponseStatus.OK);
        } else {
            message = "try again";
            jsonobject.put("status", ResponseStatus.BAD_REQUEST);
        }
        jsonobject.put("message", message);
        LOGGER.traceExit(jsonobject);
        return jsonobject;

    }

    /**
     * ReadPatient json object.
     *
     * @param patientID the patient id
     * @return patient data.
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    @GET
    @Path("/patientData/{patientID}")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject readPatient(@PathParam("patientID") int patientID) throws SystemException, BusinessException {
        LOGGER.traceEntry(String.valueOf(patientID));
        String message;
        if (patientID == -1) {
            throw new BusinessException();
        }
        record = patientDelegate.readPatient(patientID);
        if (record != null) {
            jsonobject.put("status", ResponseStatus.OK);
            jsonobject.put("message", record);
        } else {
            message = "user not found";
            jsonobject.put("status", ResponseStatus.BAD_REQUEST);
            jsonobject.put("message", message);
        }
        LOGGER.traceExit(jsonobject);
        return jsonobject;

    }

    /**
     * Gets patients doctors.
     *
     * @param patientID the patient id
     * @return the patients doctors
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    @GET
    @Path("/{patientID}/getAllDoctors")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getPatientsDoctors(@PathParam("patientID") int patientID) throws SystemException, BusinessException {
        LOGGER.traceEntry(String.valueOf(patientID));
        String message = "";
        List<DoctorRecord> doctorRecordList;
        doctorRecordList = patientDelegate.PatientDoctors(patientID);
        if (doctorRecordList != null) {
            jsonobject.put("status", ResponseStatus.OK);
            jsonobject.put("message", doctorRecordList);
        } else {
            message = "user not found";
            jsonobject.put("status", ResponseStatus.BAD_REQUEST);
            jsonobject.put("message", message);
        }
        LOGGER.traceExit(jsonobject);
        return jsonobject;
    }

    /**
     * Delete patient json object.
     *
     * @param patientID the patient id
     * @return the json object
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    @DELETE
    @Path("/delete/{patientID}")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject deletePatient(@PathParam("patientID") int patientID) throws BusinessException, SystemException {
        LOGGER.traceEntry(String.valueOf(patientID));
        String message;
        if (patientDelegate.deletePatient(patientID)) {
            jsonobject.put("status", ResponseStatus.OK);
            jsonobject.put("message", String.format("deleted %d", patientID));
        } else {
            message = "user not found";
            jsonobject.put("status", ResponseStatus.BAD_REQUEST);
            jsonobject.put("message", message);
        }
        return jsonobject;
    }

}

