package com.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mybatis.model.UserModel;

public interface UserModelMapper {
  int deleteByPrimaryKey(Integer id);

  int insert(UserModel user);

  int insertSelective(UserModel user);

  UserModel selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(UserModel user);

  int updateByPrimaryKey(UserModel user);

  int batchInsert(List<UserModel> list);

  UserModel selectByName(@Param(value = "name") String name);

  UserModel selectByName1(@Param(value = "name") String name);
}
