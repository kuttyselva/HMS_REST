package global.coda.hms.api;


import global.coda.hms.applicationconstants.ResponseStatus;
import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.dao.patient.PatientDao;
import global.coda.hms.delegates.patient.PatientDelegate;
import global.coda.hms.exceptionmappers.BusinessException;
import global.coda.hms.exceptionmappers.SystemException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
     * @return the json object
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject editPatient(@FormParam("name") String name, @FormParam("age") int age,
                                  @FormParam("location") String location, @FormParam("phone") String phone,
                                  @FormParam("disease") String disease, @FormParam("password") String password) throws SystemException, BusinessException {
        String message;
        record.setAge(age);
        record.setDisease(disease);
        record.setName(name);
        record.setPhone(phone);
        record.setPassword(password);
        record.setLocation(location);
        record.setId(patientDelegate.readPatient(name).getId());
        if (patientDelegate.updatePatient(record)) {
            message = "updated successfully";
            jsonobject.put("status", ResponseStatus.OK);
        } else {
            message = "try again";
            jsonobject.put("status", ResponseStatus.BAD_REQUEST);
        }
        jsonobject.put("message", message);
        return jsonobject;

    }

    /**
     * ReadPatient json object.
     *
     * @param patientName to get data.
     * @return patient data.
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    @GET
    @Path("/patientData/{patientName}")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject readPatient(@PathParam("patientName") String patientName) throws SystemException, BusinessException {
        String message;
        if (patientName.length() < 2) {
            throw new BusinessException();
        }
        record = patientDelegate.readPatient(patientName);
        if (record != null) {
            jsonobject.put("status", ResponseStatus.OK);
            jsonobject.put("message", record);
        } else {
            message = "user not found";
            jsonobject.put("status", ResponseStatus.BAD_REQUEST);
            jsonobject.put("message", message);
        }
        return jsonobject;

    }

    /**
     * Gets patients doctors.
     *
     * @param patientName the branch name
     * @return the patients doctors
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    @GET
    @Path("/{patientName}/getAllDoctors")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getPatientsDoctors(@PathParam("patientName") String patientName) throws SystemException, BusinessException {
        String message = "";
        List<DoctorRecord> doctorRecordList;
        doctorRecordList = patientDelegate.PatientDoctors(patientName);
        if (doctorRecordList != null) {
            jsonobject.put("status", ResponseStatus.OK);
            jsonobject.put("message", doctorRecordList);
        } else {
            message = "user not found";
            jsonobject.put("status", ResponseStatus.BAD_REQUEST);
            jsonobject.put("message", message);
        }
        return jsonobject;
    }

}

