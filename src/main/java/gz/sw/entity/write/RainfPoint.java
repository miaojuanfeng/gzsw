package gz.sw.entity.write;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;

public class RainfPoint {

	private Integer id;

	private Integer rainf;

	@Excel(name = "雨量站码")
	private Integer stid;

	@Excel(name = "权重")
	private BigDecimal weight;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRainf() {
		return rainf;
	}

	public void setRainf(Integer rainf) {
		this.rainf = rainf;
	}

	public Integer getStid() {
		return stid;
	}

	public void setStid(Integer stid) {
		this.stid = stid;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
}
