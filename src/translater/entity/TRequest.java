package translater.entity;

import java.util.List;

public class TRequest {
	
	private String sl;
	
	private String tl;
	
	private String q;
	
	private List<String> dts;
	
	public TRequest(String sl, String tl, String q, List<String> dts) {
		super();
		this.sl = sl;
		this.tl = tl;
		this.q = q;
		this.dts = dts;
	}

	public String getSl() {
		return sl;
	}

	public String getTl() {
		return tl;
	}

	public String getQ() {
		return q;
	}

	public List<String> getDts() {
		return dts;
	}

}