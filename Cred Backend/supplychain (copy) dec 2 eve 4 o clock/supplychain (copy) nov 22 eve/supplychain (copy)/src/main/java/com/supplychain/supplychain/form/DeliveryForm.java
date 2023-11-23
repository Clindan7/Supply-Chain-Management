package com.supplychain.supplychain.form;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class DeliveryForm {
    @Size(max = 30)
    @NotBlank
    private String dPersonName;
    // @Size(max = 200)
    // private String location;
    @Size(max = 30)
    private String mobile;
    @Size(max = 200)
    private String route;
    private Integer userId;
    
    public String getdPersonName() {
        return dPersonName;
    }
    public void setdPersonName(String dPersonName) {
        this.dPersonName = dPersonName;
    }

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getRoute() {
        return route;
    }
    public void setRoute(String route) {
        this.route = route;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
     
}
