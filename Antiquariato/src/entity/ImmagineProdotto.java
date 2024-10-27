package entity;

public class ImmagineProdotto {
	
	private String url;
	private String codiceProdotto;
	
	public ImmagineProdotto(String url, String codiceProdotto) {
		super();
		this.url = url;
		this.codiceProdotto = codiceProdotto;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCodiceProdotto() {
		return codiceProdotto;
	}
	public void setCodiceProdotto(String codiceProdotto) {
		this.codiceProdotto = codiceProdotto;
	}
}
