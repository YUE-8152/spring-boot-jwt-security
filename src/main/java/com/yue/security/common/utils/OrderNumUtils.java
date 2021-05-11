package com.yue.security.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: cln-school-app
 * @Package: com.cln.school.common.utils
 * @ClassName: OrderNumUtils
 * @Author: YUE
 * @Description: 订单号生成工具类
 * @Date: 2020/10/14 19:12
 * @Version: 1.0
 */
@Component
public class OrderNumUtils {
    @Autowired
    private RedisUtils redisUtils;

    public String getOrderNo() {
        String incrNo = redisUtils.incrNumStartOneEveryDay("service_package_order_code");
        String newNo = String.format("%05d", Integer.parseInt(incrNo));
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(date);
        String no = "D" + dateStr + newNo;
        return no;
    }

    public String getRefundNo() {
        String incrNo = redisUtils.incrNumStartOneEveryDay("service_package_refund_code");
        String newNo = String.format("%05d", Integer.parseInt(incrNo));
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(date);
        String no = "T" + dateStr + newNo;
        return no;
    }

    public String getSchoolPayOrderNo(String merCode) {
        String incrNo = redisUtils.incrNumStartOneEveryDay("school:pay:order:" + merCode);
        String newNo = String.format("%05d", Integer.parseInt(incrNo));
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(date);
        String no = "D" + merCode + dateStr + newNo;
        return no;
    }

    public String getSchoolPayRefundNo(String merCode) {
        String incrNo = redisUtils.incrNumStartOneEveryDay("school:pay:refund:" + merCode);
        String newNo = String.format("%05d", Integer.parseInt(incrNo));
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(date);
        String no = "T" + merCode + dateStr + newNo;
        return no;
    }
}
