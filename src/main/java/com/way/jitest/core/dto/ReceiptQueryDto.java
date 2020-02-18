package com.way.jitest.core.dto;

/**
 * @author xkm
 * @date 2020/2/18
 */
public class ReceiptQueryDto {
    private String orderNo;
    private String signInfo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSignInfo() {
        return signInfo;
    }

    public void setSignInfo(String signInfo) {
        this.signInfo = signInfo;
    }
}
