package cn.intellif.server.provider.dao;

import cn.intellif.server.provider.model.po.UserPO;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * @ClassName IUserMapper
 * @Author buchengyin
 * @Date 2019/4/1 11:02
 **/
public interface IUserMapper  extends BaseMapper<UserPO> {
    int save(UserPO userEntity);
    List<UserPO> listAll();
}
