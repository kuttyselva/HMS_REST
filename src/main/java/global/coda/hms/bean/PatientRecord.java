package global.coda.hms.bean;

/**
 * The type Patient record.
 *
 * @author VC
 */
public class PatientRecord extends UserRecord {
    /**
     * constructor.
     */
    public PatientRecord() {
        // TODO Auto-generated constructor stub
    }


    /**
     * Gets disease.
     *
     * @return disease of patient.
     */
    public String getDisease() {
        return disease;
    }

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */


    /**
     * Sets disease.
     *
     * @param disease of patient.
     */
    public void setDisease(String disease) {
        this.disease = disease;
    }

    private String disease;

    /**
     * Instantiates a new Patient record.
     *
     * @param id       of patient.
     * @param name     of patient.
     * @param age      of patient.
     * @param location of patient.
     * @param disease  of patient.
     */
    public PatientRecord(int id, String name, int age, String location, String disease) {
        this.setId(id);
        this.setAge(age);
        this.setName(name);
        this.setLocation(location);
        this.setDisease(disease);
    }
}
