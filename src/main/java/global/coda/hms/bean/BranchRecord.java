package global.coda.hms.bean;

/**
 * @author VC
 */
public class BranchRecord {
    private int id;
    private String branchName;
    private String location;
    private String hospitalName;
    private int hospitalId;

    /**
     * @return id of patient.
     */
    public int getHospitalId() {
        return hospitalId;
    }

    /**
     * @param hospitalId of patient.
     */
    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    /**
     * @return id of patient.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id of patient.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return branch of patient.
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * @param branchName of patient.
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * @return location of patient.
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location of patient.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return name of patient.
     */
    public String getHospitalName() {
        return hospitalName;
    }

    /**
     * @param hospitalName of patient.
     */
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

}
