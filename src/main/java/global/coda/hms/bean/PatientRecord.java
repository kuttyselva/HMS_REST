package global.coda.hms.bean;

/**
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
	 * @return disease of patient.
	 */
	public String getDisease() {
		return disease;
	}

	/**
	 * @param disease of patient.
	 */
	public void setDisease(String disease) {
		this.disease = disease;
	}

	private String disease;

	/**
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
	 * @return age of patient.
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age of patient.
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return name of patient.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name of patient.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return location of patient.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location2 of patient.
	 */
	public void setLocation(String location2) {
		this.location = location2;
	}

	/**
	 * @return passw of patient.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password of patient.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return phone of patient.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone of patient.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
