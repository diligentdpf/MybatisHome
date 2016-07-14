package com.test.mybatis;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mybatis.model.UserModel;
import com.mybatis.service.IUserModelService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mybatis2.xml"})
public class MybatisJunit {
  @Autowired
  private IUserModelService userModelService;
  private final static Logger logger = LoggerFactory.getLogger(MybatisJunit.class);
  private final static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

  @Test
  public void test1() {
    logger.info("开始加载根据主键ID查找对象属性");
    UserModel user = userModelService.selectByPrimaryKey(3);
    System.out.println("姓名：" + user.getName());
    System.out.println("性别：" + user.getSex());
    System.out.println("年龄：" + user.getAge());
    System.out.println("出生日期：" + FORMAT.format(user.getBirthday()));
    System.out.println("联系方式：" + user.getPhone());
    System.out.println("兴趣爱好：" + user.getHobbies());
    logger.info("加载结束！");
  }

  @Test
  public void test2() {
    logger.info("开始加载根据主键ID删除用户信息");
    int a = userModelService.deleteByPrimaryKey(1);
    if (a > 0) {
      logger.info("成功删除主键ID：" + 1 + "的用户信息");
    } else {
      logger.info("删除失败！");
    }
    logger.info("加载结束！");
  }

  @Test
  public void test3() {
    logger.info("开始加载根据主键ID修改用户信息");
    UserModel user = userModelService.selectByPrimaryKey(2);
    user.setAge(24);
    user.setBirthday(StringToDate("1990-08-12", "yyyy-MM-dd"));
    user.setPhone("15898500235");
    int a = userModelService.updateByPrimaryKeySelective(user);
    System.out.println(a);
    if (a > 0) {
      logger.info("成功修改主键ID：" + 2 + "的用户信息");
    } else {
      logger.info("修改失败！");
    }
    UserModel user1 = userModelService.selectByPrimaryKey(2);
    System.out.println("修改后ID：2的年龄：" + user1.getAge());
    System.out.println("修改后ID：2的出生日期：" + FORMAT.format(user1.getBirthday()));
    System.out.println("修改后ID：2的联系电话：" + user1.getPhone());
    logger.info("加载结束！");
  }

  @Test
  public void test4() {
    logger.info("开始加载新增用户信息");
    UserModel user = new UserModel();
    user.setName("王五");
    user.setAge(33);
    user.setSex("男");
    user.setBirthday(StringToDate("1981-05-30", "yyyy-MM-dd"));
    user.setPhone("13787899876");
    user.setHobbies("骑自行车，游戏，冲浪");
    int a = userModelService.insert(user);
    System.out.println(a);
    if (a > 0) {
      logger.info("插入数据成功！");
    } else {
      logger.info("插入数据失败！");
    }
    logger.info("加载结束！");
  }

  @Test
  public void test5() {
    logger.info("开始加载批量新增用户信息");
    List<UserModel> list = new ArrayList<UserModel>();
    UserModel user;
    for (int i = 0; i < 5; i++) {
      user = new UserModel();
      user.setName("王五" + i);
      user.setAge(33 + i);
      user.setSex("男");
      user.setBirthday(StringToDate("1981-05-30", "yyyy-MM-dd"));
      user.setPhone("13787899876");
      user.setHobbies("骑自行车，游戏，冲浪");
      list.add(user);
    }

    int a = userModelService.batchInsert(list);
    System.out.println(a);
    if (a > 0) {
      logger.info("批量插入数据成功！");
    } else {
      logger.info("批量插入数据失败！");
    }
    logger.info("加载结束！");
  }

  @Test
  public void test6() {
    logger.info("开始加载...根据用户姓名查找用户信息");
    UserModel u = userModelService.selectByName("王五");
    System.out.println("姓名：" + u.getName());
    System.out.println("性别：" + u.getSex());
    System.out.println("年龄：" + u.getAge());
    System.out.println("出生日期：" + FORMAT.format(u.getBirthday()));
    System.out.println("联系方式：" + u.getPhone());
    System.out.println("兴趣爱好：" + u.getHobbies());
    logger.info("加载结束！");
  }

  @Test
  public void test7() {
    logger.info("开始加载...根据用户姓名查找用户信息");
    UserModel u = userModelService.selectByName1("'王五1'");
    System.out.println("姓名：" + u.getName());
    System.out.println("性别：" + u.getSex());
    System.out.println("年龄：" + u.getAge());
    System.out.println("出生日期：" + FORMAT.format(u.getBirthday()));
    System.out.println("联系方式：" + u.getPhone());
    System.out.println("兴趣爱好：" + u.getHobbies());
    logger.info("加载结束！");
  }

  /**
   * @Description 字符串转换到时间格式
   * @param dateStr 需要转换的字符串
   * @param formatStr 需要格式的目标字符串 举例 yyyy-MM-dd
   * @return Date 返回转换后的时间
   * @throws ParseException 转换异常
   */
  public static Date StringToDate(String dateStr, String formatStr) {
    logger.info("开始加载（StringToDate）...字符串转换时间格式，参数，dateStr：" + dateStr + ",formatStr:" + formatStr);
    if (dateStr == null || "".equals(dateStr)) {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
    Date date = null;
    try {
      date = sdf.parse(dateStr);
    } catch (ParseException e) {
      logger.error("load ToolUtils 加载StringToDate方法.. parseException..，参数：" + dateStr, e);
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw, true));
      String str = sw.toString();
      logger.error("错误信息详情:" + str);
    }
    logger.info("加载完成（StringToDate）...结果：" + date);
    return date;
  }
}
