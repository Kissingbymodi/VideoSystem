package com.hu.video.mapper;

import com.hu.video.entity.TUser;
import com.hu.video.entity.dto.ResultDTO;
import com.hu.video.entity.dto.UserStateDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface TUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    TUser queryByuserTelAndPwd(Map<Object, String> map);

    List<TUser> queryByuserTel(String userTel);

    int register(TUser tUser);

    void updateUserByUserId(TUser tUser);

    List<UserStateDTO> userList();

    UserStateDTO getUserById(@Param("id") Long id);

    int updateUserIcon(@Param("s") String s, @Param("userId") Long userId);

    List<UserStateDTO> getUserByName(@Param("username") String username);
}
