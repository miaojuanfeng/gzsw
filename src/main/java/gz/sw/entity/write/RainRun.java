package gz.sw.entity.write;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

public class RainRun {

	private Integer id;

	@Excel(name = "stcd")
	private String stcd;

	private String name;

	@Excel(name = "lname")
	private String lname;

	@Excel(name = "lid")
	private Integer lid;

	@Excel(name = "pid")
	private Integer pid;

	@Excel(name = "pa")
	private Integer pa;

	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	public Integer getPa() {
		return pa;
	}

	public void setPa(Integer pa) {
		this.pa = pa;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
