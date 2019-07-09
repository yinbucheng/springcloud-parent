package cn.intellif.server.provider.dao;

import cn.intellif.server.provider.model.po.TestPO;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * @ClassName ITestMapper
 * @Author buchengyin
 * @Date 2019/4/20 16:26
 **/
public interface ITestMapper extends BaseMapper<TestPO> {
    void batchSave(List<TestPO> entityList);
}
