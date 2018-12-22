package login_main_etc;


//-id(pk)(id varchar(20))
//-이름(name varchar(20))
//-비밀번호(password varchar(20))
//-비밀번호확인
//-핸드폰번호(phone varchar(11)
//-주민앞자리(6자리)(jumin varchar(6))
//-이메일(email varchar(30))
public class Member {
	
	private String id;
	private String name;
	private String password;
	private String phone;
	private String jumin;
	private String email;
	private String grade;
	private int buyCount;
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getJumin() {
		return jumin;
	}
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
