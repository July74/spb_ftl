package com.july.biz.mapper;

import com.july.biz.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserEntity> selectUser();
}
