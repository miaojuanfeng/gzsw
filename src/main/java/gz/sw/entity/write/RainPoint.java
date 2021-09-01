package gz.sw.entity.write;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;

public class RainPoint {

	private Integer id;

	private Integer rain;

	@Excel(name = "水文站码")
	private String fstcd;

	@Excel(name = "雨量站码")
	private String stcd;

	@Excel(name = "权重")
	private BigDecimal weight;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRain() {
		return rain;
	}

	public void setRain(Integer rain) {
		this.rain = rain;
	}

	public String getFstcd() {
		return fstcd;
	}

	public void setFstcd(String fstcd) {
		this.fstcd = fstcd;
	}

	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
}
