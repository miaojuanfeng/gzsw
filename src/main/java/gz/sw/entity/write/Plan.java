package gz.sw.entity.write;

import java.math.BigDecimal;
import java.util.Date;

public class Plan {
	/**
	 * 业务数据
	 */
	private Integer id;
	private String stcd;
	private String name;
	private Integer rain;
	private Integer modelCl;
	private Integer modelHl;
	private Integer rainRun;
	private Integer unitLine;
	private Long createUser;
	private Date createTime;
	/**
	 * 从外部获取
	 */
	private BigDecimal KE;
	private BigDecimal XE;
	private BigDecimal INTV;
	/**
	 * 新安江河系参数
	 */
	private BigDecimal WU0;
	private BigDecimal WL0;
	private BigDecimal WD0;
	private BigDecimal WUM;
	private BigDecimal WLM;
	private BigDecimal WDM;
	private BigDecimal E;
	private BigDecimal B;
	private BigDecimal K;
	private BigDecimal C;
	private BigDecimal SM;
	private BigDecimal EX;
	private BigDecimal KSS;
	private BigDecimal KG;
	private BigDecimal IM;
	private BigDecimal CS;
	private BigDecimal CI;
	private BigDecimal CG;
	private BigDecimal L;
	private BigDecimal T;
	private BigDecimal F;
	private BigDecimal S0;
	private BigDecimal FR0;
	private BigDecimal QRS0;
	private BigDecimal QRSS0;
	private BigDecimal QRG0;
	/**
	 * 经验单位线河系参数
	 * @return
	 */
	private BigDecimal PA;
	/**
	 * API河系参数
	 */
	private BigDecimal KR;
	private BigDecimal IMM;
	private BigDecimal NA;
	private BigDecimal NU;
	private BigDecimal KU;
	private BigDecimal AREA;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRain() {
		return rain;
	}

	public void setRain(Integer rain) {
		this.rain = rain;
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

	public Integer getRainRun() {
		return rainRun;
	}

	public void setRainRun(Integer rainRun) {
		this.rainRun = rainRun;
	}

	public Integer getUnitLine() {
		return unitLine;
	}

	public void setUnitLine(Integer unitLine) {
		this.unitLine = unitLine;
	}

	//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public String getStname() {
//		return stname;
//	}
//
//	public void setStname(String stname) {
//		this.stname = stname;
//	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getKE() {
		return KE;
	}

	public void setKE(BigDecimal KE) {
		this.KE = KE;
	}

	public BigDecimal getXE() {
		return XE;
	}

	public void setXE(BigDecimal XE) {
		this.XE = XE;
	}

	public BigDecimal getWU0() {
		return WU0;
	}

	public void setWU0(BigDecimal WU0) {
		this.WU0 = WU0;
	}

	public BigDecimal getWL0() {
		return WL0;
	}

	public void setWL0(BigDecimal WL0) {
		this.WL0 = WL0;
	}

	public BigDecimal getWD0() {
		return WD0;
	}

	public void setWD0(BigDecimal WD0) {
		this.WD0 = WD0;
	}

	public BigDecimal getWUM() {
		return WUM;
	}

	public void setWUM(BigDecimal WUM) {
		this.WUM = WUM;
	}

	public BigDecimal getWLM() {
		return WLM;
	}

	public void setWLM(BigDecimal WLM) {
		this.WLM = WLM;
	}

	public BigDecimal getWDM() {
		return WDM;
	}

	public void setWDM(BigDecimal WDM) {
		this.WDM = WDM;
	}

	public BigDecimal getE() {
		return E;
	}

	public void setE(BigDecimal e) {
		E = e;
	}

	public BigDecimal getB() {
		return B;
	}

	public void setB(BigDecimal b) {
		B = b;
	}

	public BigDecimal getK() {
		return K;
	}

	public void setK(BigDecimal k) {
		K = k;
	}

	public BigDecimal getC() {
		return C;
	}

	public void setC(BigDecimal c) {
		C = c;
	}

	public BigDecimal getSM() {
		return SM;
	}

	public void setSM(BigDecimal SM) {
		this.SM = SM;
	}

	public BigDecimal getEX() {
		return EX;
	}

	public void setEX(BigDecimal EX) {
		this.EX = EX;
	}

	public BigDecimal getKSS() {
		return KSS;
	}

	public void setKSS(BigDecimal KSS) {
		this.KSS = KSS;
	}

	public BigDecimal getKG() {
		return KG;
	}

	public void setKG(BigDecimal KG) {
		this.KG = KG;
	}

	public BigDecimal getIM() {
		return IM;
	}

	public void setIM(BigDecimal IM) {
		this.IM = IM;
	}

	public BigDecimal getCS() {
		return CS;
	}

	public void setCS(BigDecimal CS) {
		this.CS = CS;
	}

	public BigDecimal getCI() {
		return CI;
	}

	public void setCI(BigDecimal CI) {
		this.CI = CI;
	}

	public BigDecimal getCG() {
		return CG;
	}

	public void setCG(BigDecimal CG) {
		this.CG = CG;
	}

	public BigDecimal getL() {
		return L;
	}

	public void setL(BigDecimal l) {
		L = l;
	}

	public BigDecimal getT() {
		return T;
	}

	public void setT(BigDecimal t) {
		T = t;
	}

	public BigDecimal getF() {
		return F;
	}

	public void setF(BigDecimal f) {
		F = f;
	}

	public BigDecimal getS0() {
		return S0;
	}

	public void setS0(BigDecimal s0) {
		S0 = s0;
	}

	public BigDecimal getFR0() {
		return FR0;
	}

	public void setFR0(BigDecimal FR0) {
		this.FR0 = FR0;
	}

	public BigDecimal getQRS0() {
		return QRS0;
	}

	public void setQRS0(BigDecimal QRS0) {
		this.QRS0 = QRS0;
	}

	public BigDecimal getQRSS0() {
		return QRSS0;
	}

	public void setQRSS0(BigDecimal QRSS0) {
		this.QRSS0 = QRSS0;
	}

	public BigDecimal getQRG0() {
		return QRG0;
	}

	public void setQRG0(BigDecimal QRG0) {
		this.QRG0 = QRG0;
	}

	public BigDecimal getPA() {
		return PA;
	}

	public void setPA(BigDecimal PA) {
		this.PA = PA;
	}

	public BigDecimal getKR() {
		return KR;
	}

	public void setKR(BigDecimal KR) {
		this.KR = KR;
	}

	public BigDecimal getIMM() {
		return IMM;
	}

	public void setIMM(BigDecimal IMM) {
		this.IMM = IMM;
	}

	public BigDecimal getNA() {
		return NA;
	}

	public void setNA(BigDecimal NA) {
		this.NA = NA;
	}

	public BigDecimal getNU() {
		return NU;
	}

	public void setNU(BigDecimal NU) {
		this.NU = NU;
	}

	public BigDecimal getKU() {
		return KU;
	}

	public void setKU(BigDecimal KU) {
		this.KU = KU;
	}

	public BigDecimal getAREA() {
		return AREA;
	}

	public void setAREA(BigDecimal AREA) {
		this.AREA = AREA;
	}
}
