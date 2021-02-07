package com.km.common.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Description: redis 操作接口
 *
 * @author GaoKunW
 * @date 2021/2/7 23:32
 */
public interface IRedisCache {
    /**
     * 保存key value 设置有效时间
     *
     * @param key
     * @param value
     * @param time
     */
    void setCacheObject(String key, Object value, long time, final TimeUnit timeUnit);

    /**
     * 保存key value
     *
     * @param key
     * @param value
     */
    void setCacheObject(String key, Object value);

    /**
     * 根据key 获取对象
     *
     * @param key
     * @return
     */
    Object getCacheObject(String key);

    /**
     * 根据key删除对象
     *
     * @param key
     * @return
     */
    Boolean deleteObject(String key);

    /**
     * 根据key列表, 批量删除对象
     *
     * @param keys
     * @return
     */
    Long deleteObject(List<String> keys);

    /**
     * 根据key 设置过期时间
     *
     * @param key 键
     * @param time 超时时间数值
     * @return true=设置成功；false=设置失败
     */
    Boolean expire(String key, long time);

    /**
     * 设置有效时间
     *
     * @param key 键
     * @param time 超时时间数值
     * @param unit 时间单位
     * @return true=设置成功；false=设置失败
     */
    Boolean expire(String key, long time, final TimeUnit unit);

    /**
     * 根据key 获取过期
     *
     * @param key
     * @return
     */
    Long getExpire(String key);

    /**
     * 根据key 判断该key是否存在
     *
     * @param key
     * @return
     */
    Boolean hasKey(String key);

    /**
     * 按delta递增
     */
    Long incr(String key, long delta);

    /**
     * 按delta递减
     */
    Long decr(String key, long delta);

    /**
     * 获取Hash结构中的属性
     */
    Object hGet(String key, String hashKey);

    /**
     * 向Hash结构中放入一个属性
     */
    Boolean hSet(String key, String hashKey, Object value, long time);

    /**
     * 向Hash结构中放入一个属性
     */
    void hSet(String key, String hashKey, Object value);

    /**
     * 直接获取整个Hash结构
     */
    Map<Object, Object> hGetAll(String key);

    /**
     * 直接设置整个Hash结构
     */
    Boolean hSetAll(String key, Map<String, Object> map, long time);

    /**
     * 直接设置整个Hash结构
     */
    void hSetAll(String key, Map<String, ?> map);

    /**
     * 删除Hash结构中的属性
     */
    void hDel(String key, Object... hashKey);

    /**
     * 判断Hash结构中是否有该属性
     */
    Boolean hHasKey(String key, String hashKey);

    /**
     * Hash结构中属性递增
     */
    Long hIncr(String key, String hashKey, Long delta);

    /**
     * Hash结构中属性递减
     */
    Long hDecr(String key, String hashKey, Long delta);

    /**
     * 获取Set结构
     */
    Set<Object> sMembers(String key);

    /**
     * 向Set结构中添加属性
     */
    Long sAdd(String key, Object... values);

    /**
     * 向Set结构中添加属性
     */
    Long sAdd(String key, long time, Object... values);

    /**
     * 是否为Set中的属性
     */
    Boolean sIsMember(String key, Object value);

    /**
     * 获取Set结构的长度
     */
    Long sSize(String key);

    /**
     * 删除Set结构中的属性
     */
    Long sRemove(String key, Object... values);

    /**
     * 获取List结构中的属性
     */
    List<Object> lRange(String key, long start, long end);

    /**
     * 获取List结构的长度
     */
    Long lSize(String key);

    /**
     * 根据索引获取List中的属性
     */
    Object lIndex(String key, long index);

    /**
     * 向List结构中添加属性
     */
    Long lPush(String key, Object value);

    /**
     * 向List结构中添加属性
     */
    Long lPush(String key, Object value, long time);

    /**
     * 向List结构中批量添加属性
     */
    Long lPushAll(String key, Object... values);

    /**
     * 向List结构中批量添加属性
     */
    Long lPushAll(String key, Long time, Object... values);

    /**
     * 从List结构中移除属性
     */
    Long lRemove(String key, long count, Object value);
}
