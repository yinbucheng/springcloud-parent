package cn.intellif.server.provider.service.db;

import cn.intellif.server.provider.entity.UserEntity;

/**
 * @ClassName IUserMapper
 * @Author buchengyin
 * @Date 2019/4/1 11:02
 **/
public interface IUserMapper {
    int save(UserEntity userEntity);
}
