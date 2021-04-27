package gz.sw.entity.write;

import java.math.BigDecimal;

public class ModelStation {

	private Integer id;

	private Integer modelId;

	private String stcd;

	private Integer planId;

	private BigDecimal ke;

	private BigDecimal xe;

	private BigDecimal intv;

	private String faStcd;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public BigDecimal getKe() {
		return ke;
	}

	public void setKe(BigDecimal ke) {
		this.ke = ke;
	}

	public BigDecimal getXe() {
		return xe;
	}

	public void setXe(BigDecimal xe) {
		this.xe = xe;
	}

	public BigDecimal getIntv() {
		return intv;
	}

	public void setIntv(BigDecimal intv) {
		this.intv = intv;
	}

	public String getFaStcd() {
		return faStcd;
	}

	public void setFaStcd(String faStcd) {
		this.faStcd = faStcd;
	}
}
