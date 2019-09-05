package com.wenbing.mvpdemo.bean;


/**
 * @author gs_wenbing
 * @date 2019/9/5 11:14
 */
public class LoginResponse {

    /**
     * UserAccountID : 122382
     * UserNo : zhangke
     * UserName : 张克
     * Phone : 15021101379
     * HeadImageUrl : http://img.61ef.cn/news/201409/28/2014092805595807.jpg
     * OrderRefundRate : 0.98
     * RewardAmountRate : 0.02
     * DepartmentName : 上海办事处
     * PlantLandlineNumber : 020-81815586
     * Salesman : 穆志生
     * SalesmanTEL : 13860166900
     * Salessuper : 周大成
     * SalessuperTEL : 13325010329
     * AddPriceRatio : 0.1
     * Password : 123456
     */

    private int UserAccountID;
    private String UserNo;
    private String UserName;
    private String Phone;
    private String HeadImageUrl;
    private double OrderRefundRate;
    private double RewardAmountRate;
    private String DepartmentName;
    private String PlantLandlineNumber;
    private String Salesman;
    private String SalesmanTEL;
    private String Salessuper;
    private String SalessuperTEL;
    private double AddPriceRatio;
    private String Password;

    public int getUserAccountID() {
        return UserAccountID;
    }

    public void setUserAccountID(int UserAccountID) {
        this.UserAccountID = UserAccountID;
    }

    public String getUserNo() {
        return UserNo;
    }

    public void setUserNo(String UserNo) {
        this.UserNo = UserNo;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getHeadImageUrl() {
        return HeadImageUrl;
    }

    public void setHeadImageUrl(String HeadImageUrl) {
        this.HeadImageUrl = HeadImageUrl;
    }

    public double getOrderRefundRate() {
        return OrderRefundRate;
    }

    public void setOrderRefundRate(double OrderRefundRate) {
        this.OrderRefundRate = OrderRefundRate;
    }

    public double getRewardAmountRate() {
        return RewardAmountRate;
    }

    public void setRewardAmountRate(double RewardAmountRate) {
        this.RewardAmountRate = RewardAmountRate;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String DepartmentName) {
        this.DepartmentName = DepartmentName;
    }

    public String getPlantLandlineNumber() {
        return PlantLandlineNumber;
    }

    public void setPlantLandlineNumber(String PlantLandlineNumber) {
        this.PlantLandlineNumber = PlantLandlineNumber;
    }

    public String getSalesman() {
        return Salesman;
    }

    public void setSalesman(String Salesman) {
        this.Salesman = Salesman;
    }

    public String getSalesmanTEL() {
        return SalesmanTEL;
    }

    public void setSalesmanTEL(String SalesmanTEL) {
        this.SalesmanTEL = SalesmanTEL;
    }

    public String getSalessuper() {
        return Salessuper;
    }

    public void setSalessuper(String Salessuper) {
        this.Salessuper = Salessuper;
    }

    public String getSalessuperTEL() {
        return SalessuperTEL;
    }

    public void setSalessuperTEL(String SalessuperTEL) {
        this.SalessuperTEL = SalessuperTEL;
    }

    public double getAddPriceRatio() {
        return AddPriceRatio;
    }

    public void setAddPriceRatio(double AddPriceRatio) {
        this.AddPriceRatio = AddPriceRatio;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}
