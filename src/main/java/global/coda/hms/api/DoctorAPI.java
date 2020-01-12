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
        if (doctorDelegate.createDoctor(record)) {
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
        record.setAge(age);
        record.setSpeciality(speciality);
        record.setName(name);
        record.setPhone(phone);
        record.setPassword(password);
        record.setLocation(location);
        record.setId(doctorDelegate.readDoctor(name).getId());
        if (doctorDelegate.updateDoctor(record)) {
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
            jsonobject.put("status", ResponseStatus.OK);
            jsonobject.put("message", patientRecordList);
        } else {
            message = "user not found";
            jsonobject.put("status", ResponseStatus.BAD_REQUEST);
            jsonobject.put("message", message);
        }
        LOGGER.traceExit(jsonobject);
        return jsonobject;
    }
}
