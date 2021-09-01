package gz.sw.entity.write;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

public class DischargePoint {

	private Integer id;

	@Excel(name = "z0")
	private Integer z0;

	@Excel(name = "hcoq")
	private Integer hcoq;

	private Integer lid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getZ0() {
		return z0;
	}

	public void setZ0(Integer z0) {
		this.z0 = z0;
	}

	public Integer getHcoq() {
		return hcoq;
	}

	public void setHcoq(Integer hcoq) {
		this.hcoq = hcoq;
	}

	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}
}
