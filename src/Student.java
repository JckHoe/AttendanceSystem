
public class Student extends User{
	private String intake;
	private String attend;
	
	public Student(String fname, String password, String bdate, String loginID, String gender, String email,String iDno, String intake) {
		super(fname, password, bdate, loginID, gender, email, iDno);
		this.intake = intake;
		this.attend = "Attended";
	}
	
	public Student(String fname, String loginID, String attend){
		super(fname,loginID);
		this.attend = attend;
	}

	public String getAttend() {
		return attend;
	}

	public void setAttend(String attend) {
		this.attend = attend;
	}

	public String getIntake() {
		return intake;
	}

	public void setIntake(String intake) {
		this.intake = intake;
	}
	
	
}
