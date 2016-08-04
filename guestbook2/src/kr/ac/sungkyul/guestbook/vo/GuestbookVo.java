package kr.ac.sungkyul.guestbook.vo;

public class GuestbookVo {
	private Long no;
	private String name;
	private String password;
	private String content;
	private String date;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "GuestbookVo [no=" + no + ", name=" + name + ", password=" + password + ", content=" + content
				+ ", date=" + date + "]";
	}
}