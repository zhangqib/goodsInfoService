package xmu.oomall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import xmu.oomall.util.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhang
 */
@Service
@Slf4j
public class IRedisService {
    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private Config config;

    /**
     * 写入redis缓存（不设置expire存活时间）
     */
    public void set(final String key, Object value){
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, config.getRedisExpireTime(), TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("写入redis缓存（设置expire存活时间）失败！错误信息为：" + e.getMessage());
        }
    }

    /**
     * 读取redis缓存
     */
    public Object get(final String key){
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            return operations.get(key);
        } catch (Exception e) {
            log.error("读取redis缓存失败！错误信息为：" + e.getMessage());
        }
        return null;
    }

    /**
     * 判断redis缓存中是否有对应的key
     */
    public boolean exists(final String key){
        Boolean result = redisTemplate.hasKey(key);
        return result == null? false: result;
    }

    /**
     * redis根据key删除对应的value
     */
    public void remove(final String key){
        try {
            if(exists(key)){
                redisTemplate.delete(key);
            }
        } catch (Exception e) {
            log.error("redis根据key删除对应的value失败！错误信息为：" + e.getMessage());
        }
    }

    /**
     * redis根据keys批量删除对应的value
     */
    public void remove(final String... keys){
        for(String key : keys){
            remove(key);
        }
    }

    /**
     * 向集合添加一个元素
     */
    public void sadd(String key, Object value) {
        SetOperations<String, Object> stringStringSetOperations = redisTemplate.opsForSet();
        stringStringSetOperations.add(key, value);
        redisTemplate.expire(key, config.getRedisExpireTime(), TimeUnit.SECONDS);
    }

    /**
     * 向集合添加一个列表的元素
     */
    public void sadd(String key, List<String> values) {
        SetOperations<String, Object> stringStringSetOperations = redisTemplate.opsForSet();
        stringStringSetOperations.add(key, values.toArray(String[]::new));
        redisTemplate.expire(key, config.getRedisExpireTime(), TimeUnit.SECONDS);
    }

    /**
     * 获取一个集合的元素
     */
    public List<String> sget(String key) {
        SetOperations<String, Object> stringObjectSetOperations = redisTemplate.opsForSet();
        List<String> values = new ArrayList<>();
        Objects.requireNonNull(stringObjectSetOperations.members(key)).forEach(value -> {
            values.add((String)value);
        });
        return values;
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}