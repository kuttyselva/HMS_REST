package global.coda.hms.api;


import global.coda.hms.applicationconstants.ResponseStatus;
import global.coda.hms.bean.PatientRecord;
import global.coda.hms.delegates.patient.PatientDeligate;
import org.json.simple.JSONObject;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * The type Patient api.
 */
@Path("/patient")
public class PatientAPI {
    private JSONObject jsonobject = new JSONObject();

    private PatientDeligate patientDeligate = new PatientDeligate();
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
     */
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject createPatient(@FormParam("name") String name, @FormParam("age") int age,
                                    @FormParam("location") String location, @FormParam("phone") String phone,
                                    @FormParam("disease") String disease, @FormParam("password") String password) {

        String message = "";
        record.setAge(age);
        record.setDisease(disease);
        record.setName(name);
        record.setPhone(phone);
        record.setPassword(password);
        record.setLocation(location);
        if (patientDeligate.createPatient(record)) {
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
     */
    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject editPatient(@FormParam("name") String name, @FormParam("age") int age,
                                  @FormParam("location") String location, @FormParam("phone") String phone,
                                  @FormParam("disease") String disease, @FormParam("password") String password) {
        String message = "";
        record.setAge(age);
        record.setDisease(disease);
        record.setName(name);
        record.setPhone(phone);
        record.setPassword(password);
        record.setLocation(location);
        record.setId(patientDeligate.readPatient(name).getId());
        if (patientDeligate.updatePatient(record)) {
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
     * Readpatient json object.
     *
     * @param patientname to get data.
     * @return patient data.
     */
    @GET
    @Path("/patientdata/{patientname}")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject readpatient(@PathParam("patientname") String patientname) {
        String message = "";
        record = patientDeligate.readPatient(patientname);
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

}

