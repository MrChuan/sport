package com.example.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * @author chuan
 */
@Component
@Slf4j
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 向redis中存入值
     * @param key
     * @param value
     * @return
     */
    public boolean setValue(String key , Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        } catch (Exception e) {
            log.error("向redis中存入值时的异常-->{}",e.getMessage());
            return false;
        }
    }

    /**
     * 向redis中存入值并指定过期时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean setValueTime(String key,Object value,long time){
        try {
            if (time > 0){
                redisTemplate.opsForValue().set(key,value,time, TimeUnit.MINUTES);
            }else {
                redisTemplate.opsForValue().set(key,value);
            }
            return true;
        } catch (Exception e) {
            log.error("设置缓存并指定过期时间异常-->{}",e.getMessage());
            return false;
        }
    }

    /**
     * 根据key获取redis中的值
     * @param key
     * @return
     */
    public Object getValue(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 根据key删除redis中的缓存
     * @param keys
     * @return
     */
    public void delKey(String... keys){
        if(keys != null && keys.length > 0){
            redisTemplate.delete(keys[0]);
        }else {
            for (String key : keys){
                redisTemplate.delete(key);
            }
        }
    }

    /**
     * 判断值是否存在redis中
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        try {
            //Boolean hasKey = redisTemplate.hasKey(key);
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("redis值不存在-->{}",e.getMessage());
            return false;
        }
    }

    /**
     * 获取redis键的过期时间
     * 0 ：永久幼小
     * 大于0 ：剩余多少分钟失效
     * @param key
     * @return
     */
    public Long isExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.MINUTES);
    }

    /**
     * 给key添加过期时间
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key, long time){
        try {
            if (time > 0){
                redisTemplate.expire(key,time,TimeUnit.MINUTES);
            }
            return true;
        }catch (Exception e){
            log.error("给旧的缓存设置新的缓存时间异常-->{}",e.getMessage());
            return false;
        }
    }

}



