package com.yue.security.dao.test;

import com.yue.security.bean.test.SysUserPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ProjectName: wx-mp-base
 * @Package: com.yx.mp.base.mapper.test
 * @ClassName: TestMapper
 * @Author: YUE
 * @Description:
 * @Date: 2021/4/21 10:16
 * @Version: 1.0
 */
@Mapper
public interface TestMapper {
    int deleteById(Integer userId);

    int insert(SysUserPo record);

    SysUserPo selectById(Integer userId);

    SysUserPo selectByUsername(String userName);

    List<SysUserPo> selectAll();

    int updateById(SysUserPo record);
}
