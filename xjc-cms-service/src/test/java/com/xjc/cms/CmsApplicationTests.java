package com.xjc.cms;

import com.common.model.Rkey;
import com.common.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.xjc.entity.system.SysUser;
import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

/**
 * 测试spring boot 自己封装的操作redis的类：StringRedisTemplate
 * 参考博客：https://www.jianshu.com/p/7bf5dc61ca06
 * <p>
 * redisTemplate.opsForValue();//操作字符串
 * redisTemplate.opsForHash();//操作hash
 * redisTemplate.opsForList();//操作list
 * redisTemplate.opsForSet();//操作set
 * redisTemplate.opsForZSet();//操作有序set
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsApplicationTests {
    private Logger logger = LoggerFactory.getLogger(CmsApplicationTests.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
        stringRedisTemplate.opsForValue().set("test", "xjc");
        String test = stringRedisTemplate.opsForValue().get("test");
        logger.info(test);
    }

    /**
     * 测试redis 对象转字符串 + 解析字符串
     * redis:String类型
     */
    @Test
    public void redisStringTest() {
        SysUser user = new SysUser();
        user.setUserId(1);
        user.setLoginName("xjc");
        user.setUserName("毛桃二");
        String obj2String = JsonUtils.toJson(user);
        stringRedisTemplate.opsForValue().set("user", obj2String);
        // 取出key = user
        String redisStr = stringRedisTemplate.opsForValue().get("user");
        if (!StringUtils.isEmpty(redisStr)) {
            // string转obj
            SysUser userRedis = JsonUtils.toBean(redisStr, new TypeReference<SysUser>() {
            });
            System.out.println(userRedis.toString());
        }
    }

    /**
     * redis:Hash类型
     * <p>
     * todo 使用 RedisTemplate这个类hash存放时，不知道为什么，key,value的前面会多出一段字符串: 如: \xAC\xED\x00\x05t\x00\x16user_info_with_student
     */
    @Test
    public void redisHashTest() {
        SysUser user = new SysUser();
        user.setUserId(1);
        user.setLoginName("xjc");
        user.setUserName("毛桃二");

        String obj2String = JsonUtils.convertObj2String(user);
        stringRedisTemplate.opsForHash().put(Rkey.USER_INFO, Rkey.KEY_PREFIX_HASH + "1", obj2String);

//		redisTemplate.opsForHash().put(Rkey.USER_INFO_WITH_STUDENT, RkeyInfo.KEY_PREFIX_HASH + "00000000000021", obj2String);
    }

}
