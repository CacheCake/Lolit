package vo;

public class TeamAction {

	private long ip;
	private String time;
	private String browser;
	private int visitCount;
	private int registCount;

	public long getIp() {
		return ip;
	}

	public void setIp(long ip) {
		this.ip = ip;
	}

	public int getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public int getRegistCount() {
		return registCount;
	}

	public void setRegistCount(int registCount) {
		this.registCount = registCount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}
}
