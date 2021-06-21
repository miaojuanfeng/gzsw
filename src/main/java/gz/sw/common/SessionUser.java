package gz.sw.common;

/**
 * {{文件描述}}
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2021年06月10日
 */
public class SessionUser {

    private Integer id;

    private String phone;

    private String name;

    private Integer admin;

    private Forecast forecast;

    public SessionUser(){
        forecast = new Forecast();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}
