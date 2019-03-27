
public abstract class User {
	private String fname;
	private String password;
	private String Bdate;
	private String loginID;
	private String gender;
	private String email;
	private String IDno;
	
	public User(String fname, String password, String bdate, String loginID, String gender, String email, String iDno) {
		this.fname = fname;
		this.password = password;
		Bdate = bdate;
		this.loginID = loginID;
		this.gender = gender;
		this.email = email;
		IDno = iDno;
	}
	
	public User(String fname, String loginID){
		this.fname = fname;
		this.loginID = loginID;
		this.password = "";
		this.Bdate = "";
		this.gender = "";
		this.email = "";
		this.IDno ="";
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBdate() {
		return Bdate;
	}

	public void setBdate(String bdate) {
		Bdate = bdate;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIDno() {
		return IDno;
	}

	public void setIDno(String iDno) {
		IDno = iDno;
	}
	
	
	
}
