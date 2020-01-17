package global.coda.hms.api;

import global.coda.hms.applicationconstants.ResponseStatus;
import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.delegates.doctor.DoctorDelegate;
import global.coda.hms.exceptionmappers.BusinessException;
import global.coda.hms.exceptionmappers.SystemException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import javax.ws.rs.Consumes;
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
 * The type Doctor api.
 */
@Path("/doctor")
public class DoctorAPI {

    private JSONObject jsonobject = new JSONObject();
    private static final Logger LOGGER = LogManager.getLogger(DoctorAPI.class);
    private DoctorDelegate doctorDelegate = new DoctorDelegate();
    private DoctorRecord record = new DoctorRecord();

    /**
     * Instantiates a new Doctor api.
     *
     * @throws SQLException the sql exception
     */
    public DoctorAPI() throws SQLException {
    }

    /**
     * CreatePatient json object.
     *
     * @param name       the name
     * @param age        the age
     * @param location   the location
     * @param phone      the phone
     * @param speciality the speciality
     * @param password   the password
     * @return the json object
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject createDoctor(@FormParam("name") String name, @FormParam("age") int age,
                                   @FormParam("location") String location, @FormParam("phone") String phone,
                                   @FormParam("speciality") String speciality, @FormParam("password") String password) throws SystemException, BusinessException {
        LOGGER.traceEntry(name, age, location, phone, speciality);
        String message;
        record.setAge(age);
        record.setSpeciality(speciality);
        record.setName(name);
        record.setPhone(phone);
        record.setPassword(password);
        record.setLocation(location);
        //FIX
        if (doctorDelegate.createDoctor(record)) {
            message = ResponseStatus.CREATE_DOC;
            jsonobject.put(ResponseStatus.STATUS, ResponseStatus.OK);
        } else {
            message = ResponseStatus.USER_NOT_FOUND;
            jsonobject.put(ResponseStatus.STATUS, ResponseStatus.BAD_REQUEST);
        }
        jsonobject.put(ResponseStatus.MESSAGE, message);
        LOGGER.traceExit(jsonobject);
        return jsonobject;

    }

    /**
     * Edit patient json object.
     *
     * @param name       the name
     * @param age        the age
     * @param location   the location
     * @param phone      the phone
     * @param speciality the speciality
     * @param password   the password
     * @return the json object
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject editDoctor(@FormParam("name") String name, @FormParam("age") int age,
                                 @FormParam("location") String location, @FormParam("phone") String phone,
                                 @FormParam("speciality") String speciality, @FormParam("password") String password) throws SystemException, BusinessException {
        LOGGER.traceEntry(name, age, location, phone, speciality);
        String message;
        //FIX
        record.setAge(age);
        record.setSpeciality(speciality);
        record.setName(name);
        record.setPhone(phone);
        record.setPassword(password);
        record.setLocation(location);
        record.setId(doctorDelegate.readDoctor(name).getId());
        //FIX
        if (doctorDelegate.updateDoctor(record)) {
            message = ResponseStatus.UPDATE_DOC;
            jsonobject.put(ResponseStatus.STATUS, ResponseStatus.OK);
        } else {
            message = ResponseStatus.USER_NOT_FOUND;
            jsonobject.put(ResponseStatus.STATUS, ResponseStatus.BAD_REQUEST);
        }
        jsonobject.put(ResponseStatus.MESSAGE, message);
        LOGGER.traceExit(jsonobject);
        return jsonobject;

    }

    /**
     * readDoctor json object.
     *
     * @param doctorName the doctor name
     * @return patient data.
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    @GET
    @Path("/doctorData/{doctorName}")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject readDoctor(@PathParam("doctorName") String doctorName) throws SystemException, BusinessException {
        LOGGER.traceEntry(doctorName);
        String message;
        record = doctorDelegate.readDoctor(doctorName);
        if (record != null) {
            jsonobject.put(ResponseStatus.STATUS, ResponseStatus.OK);
            jsonobject.put(ResponseStatus.MESSAGE, record);
        } else {
            message = ResponseStatus.USER_NOT_FOUND;
            jsonobject.put(ResponseStatus.STATUS, ResponseStatus.BAD_REQUEST);
            jsonobject.put(ResponseStatus.MESSAGE, message);
        }
        LOGGER.traceExit(jsonobject);
        return jsonobject;

    }

    /**
     * Gets patients doctors.
     *
     * @param doctorName the doctor name
     * @return the patients doctors
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    @GET
    @Path("/{doctorName}/getAllPatients")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getDoctorPatients(@PathParam("doctorName") String doctorName) throws SystemException, BusinessException {
        LOGGER.traceEntry(doctorName);
        String message;
        List<PatientRecord> patientRecordList;
        patientRecordList = doctorDelegate.getAllPatients(doctorName);
        if (patientRecordList != null) {
            jsonobject.put(ResponseStatus.STATUS, ResponseStatus.OK);
            jsonobject.put(ResponseStatus.MESSAGE, patientRecordList);
        } else {
            message = ResponseStatus.USER_NOT_FOUND;
            jsonobject.put(ResponseStatus.STATUS, ResponseStatus.BAD_REQUEST);
            jsonobject.put(ResponseStatus.MESSAGE, message);
        }
        LOGGER.traceExit(jsonobject);
        return jsonobject;
    }
//

    /**
     * Gets all doctors.
     *
     * @return the all doctors
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    @POST
    @Path("all/patient")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public JSONObject getAllDoctorsPatient() throws BusinessException, SystemException {
        List<DoctorRecord> doctorPatientsLists;
        doctorPatientsLists = doctorDelegate.readAllDoctorsPatientsDelegate();
        jsonobject.put(ResponseStatus.STATUS, ResponseStatus.OK);
        jsonobject.put(ResponseStatus.MESSAGE, doctorPatientsLists);
        LOGGER.trace(jsonobject);
        return jsonobject;
    }

    /**
     * Delete doctor json object.
     *
     * @param doctorID the doctor id
     * @return the json object
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    @DELETE
    @Path("/delete/{doctorID}")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject deleteDoctor(@PathParam("doctorID") int doctorID) throws BusinessException, SystemException {
        LOGGER.traceEntry(String.valueOf(doctorID));
        String message;
        if (doctorDelegate.deleteDoctor(doctorID)) {
            jsonobject.put(ResponseStatus.STATUS, ResponseStatus.SUCCESS_NO_CONTENT);
            jsonobject.put(ResponseStatus.MESSAGE, ResponseStatus.DELETE_PAT);
        } else {
            message = ResponseStatus.USER_NOT_FOUND;
            jsonobject.put(ResponseStatus.STATUS, ResponseStatus.BAD_REQUEST);
            jsonobject.put(ResponseStatus.MESSAGE, message);
        }
        return jsonobject;
    }
}
