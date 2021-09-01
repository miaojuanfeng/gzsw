package gz.sw.entity.write;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

public class UnitLinePoint {

	private Integer id;

	@Excel(name = "h")
	private String h;

	@Excel(name = "f")
	private String f;

	@Excel(name = "ptno")
	private String ptno;

	@Excel(name = "lid")
	private Integer lid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getH() {
		return h;
	}

	public void setH(String h) {
		this.h = h;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getPtno() {
		return ptno;
	}

	public void setPtno(String ptno) {
		this.ptno = ptno;
	}

	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}
}
