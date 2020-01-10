package global.coda.hms.bean;

/**
 * @author VC
 */
public class DoctorRecord {
	private int id;
	private int age;
	private String name;
	private String location;
	private String password;
	private String phone;
	private String speciality;

	/**
	 * gets id.
	 * @return id userId.
	 */
	public int getId() {
		return id;
	}

	/**
	 * gets age.
	 * @return age users age.
	 */
	public int getAge() {
		return age;
	}

	/**
	 * sets id.
	 * @param id userid.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * sets age.
	 * @param age user age.
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * gets speciality.
	 * @return speciality job of doc.
	 */

	public String getSpeciality() {
		return speciality;
	}

	/**
	 * sets age.
	 * @param speciality user job.
	 */
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	@Override
	public String toString() {
		return "DoctorRecord{" + "age='" + age + '\'' + ", name='" + name + '\'' + ", location='" + location + '\''
				+ ", password='" + password + '\'' + ", phone='" + phone + '\'' + '}';
	}

	/**
	 * gets name.
	 * @return name name of doc.
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets name.
	 * @param name name of doc
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * gets location.
	 * @return location location of doc.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * sets location.
	 * @param location loc of doc
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * gets password.
	 * @return password password of doc.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * sets password.
	 * @param password doc password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * gets phone.
	 * @return phone phone number of doc.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * sets phone.
	 * @param phone doc phone number.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
