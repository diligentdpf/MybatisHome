package com.mybatis.service;

import java.util.List;

import com.mybatis.model.UserModel;

public interface IUserModelService {
  public UserModel selectByPrimaryKey(Integer id);

  public int deleteByPrimaryKey(Integer id);

  public int updateByPrimaryKeySelective(UserModel user);

  public int insert(UserModel user);

  public int batchInsert(List<UserModel> list);

  public UserModel selectByName(String name);

  public UserModel selectByName1(String name);
}
