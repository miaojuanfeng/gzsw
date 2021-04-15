package gz.sw.entity.write;

import java.math.BigDecimal;

public class ModelStation {

	private Integer id;

	private Integer modelId;

	private String stcd;

	private Integer planCl;

	private Integer planHl;

	private Integer modelCl;

	private Integer modelHl;

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

	public Integer getPlanCl() {
		return planCl;
	}

	public void setPlanCl(Integer planCl) {
		this.planCl = planCl;
	}

	public Integer getPlanHl() {
		return planHl;
	}

	public void setPlanHl(Integer planHl) {
		this.planHl = planHl;
	}

	public Integer getModelCl() {
		return modelCl;
	}

	public void setModelCl(Integer modelCl) {
		this.modelCl = modelCl;
	}

	public Integer getModelHl() {
		return modelHl;
	}

	public void setModelHl(Integer modelHl) {
		this.modelHl = modelHl;
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
