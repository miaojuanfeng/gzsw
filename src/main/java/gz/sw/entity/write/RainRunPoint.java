package gz.sw.entity.write;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

public class RainRunPoint {

	private Integer id;

	@Excel(name = "lid")
	private Integer lid;

	@Excel(name = "pino")
	private String pino;

	@Excel(name = "r")
	private String r;

	@Excel(name = "d")
	private String d;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	public String getPino() {
		return pino;
	}

	public void setPino(String pino) {
		this.pino = pino;
	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}
}
