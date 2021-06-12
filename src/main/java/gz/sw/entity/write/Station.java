package gz.sw.entity.write;

import java.math.BigDecimal;
import java.util.Date;

public class Station {
	private Integer id;
	private String stcd;
	private String type;
	private String stname;
//	private String wea;
//	private String fileCd;
//	private Float jbLine;
//	private Float jjLine;
//	private String userStcd;
	private BigDecimal lgtd;
	private BigDecimal lttd;
	private BigDecimal dis;
	private String nearStcd;
	private BigDecimal selfP;
	private BigDecimal nearP;
	private String diffP;
	private String dateP;

	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStname() {
		return stname;
	}

	public void setStname(String stname) {
		this.stname = stname;
	}

	public BigDecimal getLgtd() {
		return lgtd;
	}

	public void setLgtd(BigDecimal lgtd) {
		this.lgtd = lgtd;
	}

	public BigDecimal getLttd() {
		return lttd;
	}

	public void setLttd(BigDecimal lttd) {
		this.lttd = lttd;
	}

	public BigDecimal getDis() {
		return dis;
	}

	public void setDis(BigDecimal dis) {
		this.dis = dis;
	}

	public String getNearStcd() {
		return nearStcd;
	}

	public void setNearStcd(String nearStcd) {
		this.nearStcd = nearStcd;
	}

	public BigDecimal getSelfP() {
		return selfP;
	}

	public void setSelfP(BigDecimal selfP) {
		this.selfP = selfP;
	}

	public BigDecimal getNearP() {
		return nearP;
	}

	public void setNearP(BigDecimal nearP) {
		this.nearP = nearP;
	}

	public String getDiffP() {
		return diffP;
	}

	public void setDiffP(String diffP) {
		this.diffP = diffP;
	}

	public String getDateP() {
		return dateP;
	}

	public void setDateP(String dateP) {
		this.dateP = dateP;
	}
}
