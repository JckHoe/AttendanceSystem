
public class Lecturer extends User{
	private String marStat;
	
	public Lecturer(String fname, String password, String bdate, String loginID, String gender, String email,String iDno, String marStat) {
		super(fname, password, bdate, loginID, gender, email, iDno);
		this.marStat = marStat;
	}

	public String getMarStat() {
		return marStat;
	}

	public void setMarStat(String marStat) {
		this.marStat = marStat;
	}
}
