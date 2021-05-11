package com.yue.security.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yue.security.common.core.Result;
import com.yue.security.common.core.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ProjectName: wx-mp-base
 * @Package: com.yx.mp.base.common.utils
 * @ClassName: MpMenuUtils
 * @Author: YUE
 * @Description:
 * @Date: 2021/4/21 11:29
 * @Version: 1.0
 */
public class MpMenuUtils {
    private static final Logger logger = LoggerFactory.getLogger(MpMenuUtils.class);

    /**
     * 创建button类型菜单
     *
     * @param accessToken:
     * @return: com.yx.mp.base.common.core.Result
     * @Author: YUE
     * @Date: 2021/4/21 11:52
     **/
    public static Result createButtonMenu(String accessToken) {
//        String jsonMenu = createButtonMenu();
        String jsonMenu = createXYButtonMenu();
        logger.info("====>创建公众号菜单 参数：" + jsonMenu);
        String path = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
        try {
            URL url = new URL(path);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setDoOutput(true);
            http.setDoInput(true);
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.connect();
            OutputStream os = http.getOutputStream();
            os.write(jsonMenu.getBytes("UTF-8"));
            os.close();

            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] bt = new byte[size];
            is.read(bt);
            String message = new String(bt, "UTF-8");
            JSONObject jsonMsg = JSONObject.parseObject(message);

            logger.info("====>创建公众号菜单 返回结果：" + jsonMenu);
            int status = Integer.parseInt(jsonMsg.getString("errcode"));
            if (status == 0) {
                return Result.success();
            }
            return Result.fail("创建公众号菜单失败", jsonMsg);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("创建公众号菜单失败,请求异常");
        }
    }

    /**
     * 创建多有三个菜单
     *
     * @param accessToken:
     * @return: com.yx.mp.base.common.core.Result
     * @Author: YUE
     * @Date: 2021/4/21 11:53
     **/
    public static Result createMultipleMenu(String accessToken) {
        String jsonMenu = createMultipleMenu();
        logger.info("====>创建公众号菜单 参数：" + jsonMenu);
        String path = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
        try {
            URL url = new URL(path);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setDoOutput(true);
            http.setDoInput(true);
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.connect();
            OutputStream os = http.getOutputStream();
            os.write(jsonMenu.getBytes("UTF-8"));
            os.close();

            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] bt = new byte[size];
            is.read(bt);
            String message = new String(bt, "UTF-8");
            JSONObject jsonMsg = JSONObject.parseObject(message);

            logger.info("====>创建公众号菜单 返回结果：" + jsonMenu);
            int status = Integer.parseInt(jsonMsg.getString("errcode"));
            if (status == 0) {
                return Result.success();
            }
            return Result.fail("创建公众号菜单失败", jsonMsg);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("创建公众号菜单失败,请求异常");
        }
    }

    private static String createXYButtonMenu() {
        JSONObject jsonObject = new JSONObject();
        JSONArray btnArr = new JSONArray();
        //一级菜单
        JSONObject menu1 = new JSONObject();
        menu1.put("name", "百度");
        menu1.put("type", "view");
        menu1.put("url", "http://www.baidu.com");
        btnArr.add(menu1);

        //一级菜单
        JSONObject menu2 = new JSONObject();
        menu2.put("name", "公众号");
        menu2.put("type", "view");
        menu2.put("url", "https://mp.weixin.qq.com");
        btnArr.add(menu2);

        //一级菜单
        JSONObject menu3 = new JSONObject();
        menu3.put("name", "GitHub");
        menu3.put("type", "view");
        menu3.put("url", "https://github.com/");
        btnArr.add(menu3);

        jsonObject.put("button", btnArr);
        return jsonObject.toJSONString();
    }

    private static String createButtonMenu() {
        JSONObject jsonObject = new JSONObject();
        JSONArray btnArr = new JSONArray();
        //一级菜单
        JSONObject menu1 = new JSONObject();
        menu1.put("name", "惠民商城");
        menu1.put("type", "miniprogram");
        menu1.put("url", "http://mp.weixin.qq.com");
        menu1.put("appid", "wx988f5db282500323");
        menu1.put("pagepath", "pages/index/index");
        btnArr.add(menu1);

        //一级菜单
        JSONObject menu2 = new JSONObject();
        menu2.put("name", "云访客");
        menu2.put("type", "miniprogram");
        menu2.put("url", "http://mp.weixin.qq.com");
        menu2.put("appid", "wx47f07c0ac4935950");
        menu2.put("pagepath", "parkPages/visitor/visitor");
        btnArr.add(menu2);

        //一级菜单
        JSONObject menu3 = new JSONObject();
        menu3.put("name", "云访客");
        menu3.put("type", "miniprogram");
        menu3.put("url", "http://mp.weixin.qq.com");
        menu3.put("appid", "wx47f07c0ac4935950");
        menu3.put("pagepath", "parkPages/visitor/visitor");
        btnArr.add(menu3);

        jsonObject.put("button", btnArr);
        return jsonObject.toJSONString();
    }

    private static String createMultipleMenu() {
        JSONObject jsonObject = new JSONObject();
        JSONArray btnArr = new JSONArray();
        //一级菜单
        JSONObject menu1 = new JSONObject();
        menu1.put("name", "进入邦客惠校园");
        menu1.put("type", "miniprogram");
        menu1.put("url", "http://mp.weixin.qq.com");
        menu1.put("appid", "wxb16bc1912a87168e");
        menu1.put("pagepath", "pages/login/login");
        btnArr.add(menu1);

        //一级菜单
        JSONObject menu3 = new JSONObject();
        menu3.put("name", "家长绑定学生");
        menu3.put("type", "miniprogram");
        menu3.put("url", "http://mp.weixin.qq.com");
        menu3.put("appid", "wxb16bc1912a87168e");
        menu3.put("pagepath", "minePages/parents-relevance/parents-relevance");
        btnArr.add(menu3);

        //一级菜单
        JSONObject menu2 = new JSONObject();
        menu2.put("name", "使用帮助");
        menu2.put("type", "click");

        JSONArray subBtnArr = new JSONArray();
        //二级菜单
        JSONObject subMenu2_1 = new JSONObject();
        subMenu2_1.put("name", "如何关联学生");
        subMenu2_1.put("type", "view");
        subMenu2_1.put("url", "https://mp.weixin.qq.com/s/-v6CWnyCOCv5fWzSL-lmFg");
        subBtnArr.add(subMenu2_1);
        JSONObject subMenu2_2 = new JSONObject();
        subMenu2_2.put("name", "如何请假");
        subMenu2_2.put("type", "view");
        subMenu2_2.put("url", "https://mp.weixin.qq.com/s/lQ4A2nPklClIP9D_H6VvXg");
        subBtnArr.add(subMenu2_2);
        JSONObject subMenu2_3 = new JSONObject();
        subMenu2_3.put("name", "上传人脸照");
        subMenu2_3.put("type", "view");
        subMenu2_3.put("url", "https://mp.weixin.qq.com/s/sH91J2spsjmb_voubfT4kQ");
        subBtnArr.add(subMenu2_3);
        menu2.put("sub_button", subBtnArr);


        btnArr.add(menu2);

        jsonObject.put("button", btnArr);
        return jsonObject.toJSONString();
    }
}
