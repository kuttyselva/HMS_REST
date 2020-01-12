package global.coda.hms.bean;

/**
 * The type Patient record.
 *
 * @author VC
 */
public class PatientRecord {
	/**
	 * constructor.
	 */
	public PatientRecord() {
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "PatientRecord [id=" + id + ", age=" + age + ", name=" + name + ", location=" + location + ", password="
				+ password + ", phone=" + phone + ", disease=" + disease + "]";
	}

	private int id;
	private int age;
	private String name;
	private String location;
	private String password;
	private String phone;

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
	public boolean isEmpty() {
		if (name == null || password == null || id == -1 || location == null || age == -1 || phone == null) {
			return true;
		}
		return false;
	}

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

	/**
	 * Gets id.
	 *
	 * @return id of patient.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id of patient.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets age.
	 *
	 * @return age of patient.
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets age.
	 *
	 * @param age of patient.
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Gets name.
	 *
	 * @return name of patient.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 *
	 * @param name of patient.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets location.
	 *
	 * @return location of patient.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets location.
	 *
	 * @param location2 of patient.
	 */
	public void setLocation(String location2) {
		this.location = location2;
	}

	/**
	 * Gets password.
	 *
	 * @return passw of patient.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password.
	 *
	 * @param password of patient.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets phone.
	 *
	 * @return phone of patient.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets phone.
	 *
	 * @param phone of patient.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
