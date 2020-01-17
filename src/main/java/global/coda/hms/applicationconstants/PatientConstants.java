package global.coda.hms.applicationconstants;

/**
 * @author VC
 */
public class PatientConstants {
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SIX = 6;


    public static final String SQLQUEIRES = "sqlqueries";
    public static final String DOCTOR_IN_BRANCH = "DOCB004Q";

    public static final String PATIENT_UPDATE = "PERUPD0Q";
    public static final String PATIENT_DELETE = "PADL000Q";
    public static final String PATIENT_TABLE_UPDATE = "PATUP00Q";
    public static final String PATIENT_RECORD = "PATDET0Q";
    public static final String PATIENT_CREATE_USER = "PATCU00Q";
    public static final String PATIENT_CREATE_PATIENT = "PATCP00Q";
    public static final String PATIENT_DOCTORS = "PADC001Q";

//DAO ERRORS

    public static final String ERR_PAT_CRT = "Error occurred in Creating Patient";

    public static final String ERR_PAT_RED = "Error occurred in Reading Patient";

    public static final String ERR_PAT_UPD = "Error occurred in Updating Patient";
    public static final String ERR_PAT_DOC = "Error occurred in getting Patient's doctors";
}
