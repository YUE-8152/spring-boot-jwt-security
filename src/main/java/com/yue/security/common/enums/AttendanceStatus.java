/**
 * @Title: AttendanceStatus.java
 * @Package com.cln.bi.service.impl.wisdom.attendance
 * @Description: 学生考勤接口实现类包
 * @author YiShan
 * @date 2020年8月14日 下午3:52:24
 * @version V1.0
 */
package com.yue.security.common.enums;

/**
 * @author YiShan
 * @ClassName: AttendanceStatus
 * @Description: 考勤状态枚举类
 * @date 2020年8月14日 下午3:52:24
 * @Company 深圳市卡联科技股份有限公司
 */
public enum AttendanceStatus {
    NORMAL(0, "正常"),

    LATE(1, "迟到"),

    ABSENTEEISM(2, "旷课"),

    LEAVEEARLY(3, "早退"),

    VACATION(4, "请假");


    private Integer status;
    private String desc;

    /**
     * @param status
     * @param desc
     */
    private AttendanceStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

}
