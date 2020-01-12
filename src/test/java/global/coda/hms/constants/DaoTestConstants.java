package global.coda.hms.constants;


import global.coda.hms.bean.DoctorRecord;
import global.coda.hms.bean.PatientRecord;

import java.util.Random;

/**
 *
 * @author Vijay
 *
 * Constants for DaoTest class.
 */
public final class DaoTestConstants {

	/**
	 *
	 */
	private DaoTestConstants() {

	}

	public static final String BRANCHNAME = "abc";
	public static final String PHONE = "56788998";
	public static final String HOSPITALNAME = "apollo";
	public static final int WEIGHT = 70;
	public static final String EMAIL = "abc@gmail.com";
	public static final String PASSWORD = "123";

	/**
	 *
	 * @return Branch
	 */
//	public static Branch getBranch() {
//		Branch branch = new Branch();
//		branch.setBranchName("t nagar");
//		branch.setHospitalId(2);
//		return branch;
//	}

	/**
	 *
	 * @return Doctor
	 */
	public static DoctorRecord getDoctor() {
		DoctorRecord user = new DoctorRecord();
		user.setName("doc1");
		user.setPhone("1234");
		user.setPassword("1234");
		user.setAge(20);
		user.setSpeciality("kids");
		user.setLocation("madurai");
		return user;
	}

//	/**
//	 *
//	 * @return Hospital
//	 */
//	public static Hospital getHospital() {
//		Hospital hospital = new Hospital();
//		hospital.setHospitalName("siims");
//		return hospital;
//	}

	/**
	 *
	 * @return Patient
	 */
	public static PatientRecord getPatient() {
		PatientRecord user = new PatientRecord();
		user.setName("selva");
		user.setPhone("2345678998");
		user.setLocation("doloaewr");
		user.setPassword(null);
		user.setDisease("fever");
		user.setAge(23);
		user.setId(3);
		return user;
	}

	public static PatientRecord createPatient() {
		PatientRecord user = new PatientRecord();
		user.setName(String.valueOf(new Random()));
		user.setPhone("2345678998");
		user.setLocation("doloaewr");
		user.setPassword(String.valueOf(new Random()));
		user.setDisease("fever");
		user.setAge(23);
		user.setId(3);
		return user;
	}



}
