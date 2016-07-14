package com.mybatis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybatis.dao.UserModelMapper;
import com.mybatis.model.UserModel;
import com.mybatis.service.IUserModelService;

@Service("userService")
public class UserModelService implements IUserModelService {
  @Autowired
  private UserModelMapper userModelMapper;

  @Override
  public UserModel selectByPrimaryKey(Integer id) {
    return userModelMapper.selectByPrimaryKey(id);
  }

  @Override
  public int deleteByPrimaryKey(Integer id) {
    return userModelMapper.deleteByPrimaryKey(id);
  }

  @Override
  public int updateByPrimaryKeySelective(UserModel user) {
    return userModelMapper.updateByPrimaryKeySelective(user);
  }

  @Override
  public int insert(UserModel user) {
    return userModelMapper.insert(user);
  }

  @Override
  public int batchInsert(List<UserModel> list) {
    return userModelMapper.batchInsert(list);
  }

  @Override
  public UserModel selectByName(String name) {
    return userModelMapper.selectByName(name);
  }

  @Override
  public UserModel selectByName1(String name) {
    return userModelMapper.selectByName1(name);
  }

}
