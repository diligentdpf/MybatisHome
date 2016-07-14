package com.mybatis.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybatis.model.UserModel;
import com.mybatis.service.IUserModelService;

@Controller
public class UserController {
  @Autowired
  private IUserModelService userService;
  private final Logger logger = LoggerFactory.getLogger(UserController.class);

  @RequestMapping(value = "/getUser")
  @ResponseBody
  public Map<String, Object> getUser() {
    logger.info("开始加载！！");
    Map<String, Object> resultMap = new HashMap<String, Object>();
    UserModel userModel = userService.selectByPrimaryKey(14);
    resultMap.put("state", "success");
    resultMap.put("user", userModel);
    return resultMap;
  }
}
