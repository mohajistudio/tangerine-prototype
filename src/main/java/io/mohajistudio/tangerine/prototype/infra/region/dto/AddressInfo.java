package io.mohajistudio.tangerine.prototype.infra.region.dto;

import lombok.Data;

@Data
public class AddressInfo {
    private  String province;//광역시/도
    private  String city;//시/군/구
    private  String district;//읍/면/동
    private  String detail;//이하



    public AddressInfo(String province, String city, String district, String detail) {
        this.province = province;
        this.city = city;
        this.district = district;
        this.detail = detail;
    }
}
