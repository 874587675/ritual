package com.ruoyi.framework.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * spring redis 工具类
 *
 * @author ruoyi
 **/
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
public class RedisCache {
    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获取有效时间
     *
     * @param key Redis键
     * @return 有效时间
     */
    public long getExpire(final String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 判断 key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return
     */
    public boolean deleteObject(final Collection collection) {
        return redisTemplate.delete(collection) > 0;
    }

    /**
     * 缓存List数据
     * Redis List 是一个双端队列，可以高效地从两端进行操作（如 leftPush, rightPush, leftPop, rightPop）
     * 适用于需要按顺序存储数据的场景，比如队列、日志记录、任务队列等。
     *适合处理顺序性的数据，允许重复元素
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> long setCacheList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 缓存Set
     * 适用于去重、唯一性要求的场景，比如存储不重复的标签、用户的收藏集、唯一用户ID等。
     *用于集合运算，确保元素唯一性并支持交集、并集、差集等操作。
     * @param key     缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext()) {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    /**
     * 获得缓存的set
     *
     * @param key
     * @return
     */
    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往Hash中存入数据
     *
     * @param key   Redis键
     * @param hKey  Hash键
     * @param value 值
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key   Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 删除Hash中的某条数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     * @return 是否成功
     */
    public boolean deleteCacheMapValue(final String key, final String hKey) {
        return redisTemplate.opsForHash().delete(key, hKey) > 0;
    }

    /**
     * 获得缓存的基本对象列表
     *  排行榜：利用 ZSet 存储用户的分数，然后按分数排名。
     * 任务调度：利用 ZSet 存储任务的优先级，然后按优先级（分数）执行任务。
     * 实时排名：可以使用 ZSet 快速查询前 N 或后 N 的排名。
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 缓存ZSet数据
     * 向 Redis ZSet 添加元素，并指定分数
     *
     * @param key   Redis的键
     * @param value 要存储的元素
     * @param score 分数
     * @return 添加状态
     */
    public <T> Boolean setCacheZSet(final String key, final T value, final double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 获得ZSet中的元素，按分数从小到大排列
     *
     * @param key Redis的键
     * @return 按分数排序的元素
     */
    public <T> Set<T> getCacheZSet(final String key) {
        return redisTemplate.opsForZSet().range(key, 0, -1); // 获取按分数从小到大的元素
    }

    /**
     * 获得ZSet中的元素，按分数从小到大排列
     *
     * @param key Redis的键
     * @return 按分数排序的元素
     */
    public <T> Set<T> getCacheZSetReverse(final String key) {
        return redisTemplate.opsForZSet().reverseRange(key, 0, -1); // 获取按分数从小到大的元素
    }

    /**
     * 获取ZSet中指定范围内的元素（按分数从小到大排序）
     *
     * @param key    Redis的键
     * @param start  起始索引
     * @param end    结束索引
     * @return 指定范围内的元素
     */
    public <T> Set<T> getCacheZSetRange(final String key, final long start, final long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 获取ZSet中指定范围内的元素（按分数从大到小排序）
     *
     * @param key    Redis的键
     * @param start  起始索引
     * @param end    结束索引
     * @return 指定范围内的元素
     */
    public <T> Set<T> getCacheZSetRangeReverse(final String key, final long start, final long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 获取ZSet中指定分数范围内的元素（按分数从小到大排名）
     *
     * @param key    Redis的键
     * @param min    最小分数
     * @param max    最大分数
     * @return 指定分数范围内的元素
     */
    public <T> Set<T> getCacheZSetByScore(final String key, final double min, final double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    /**
     * 获取ZSet中指定分数范围内的元素（按分数从大到小排名）
     *
     * @param key    Redis的键
     * @param min    最小分数
     * @param max    最大分数
     * @return 指定分数范围内的元素
     */
    public <T> Set<T> getCacheZSetByScoreReverse(final String key, final double min, final double max) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    /**
     * 获取ZSet中指定元素的排名（按分数从小到大排名）
     *
     * @param key   Redis的键
     * @param value 要查询排名的元素
     * @return 元素的排名，若不存在，返回null
     */
    public <T> Long getCacheZSetRank(final String key, final T value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * 获取ZSet中指定元素的排名（按分数从大到小排名）
     *
     * @param key   Redis的键
     * @param value 要查询排名的元素
     * @return 元素的排名，若不存在，返回null
     */
    public <T> Long getCacheZSetRankReverse(final String key, final T value) {
        return redisTemplate.opsForZSet().reverseRank(key, value);
    }

    /**
     * 获取ZSet中指定元素的分数
     *
     * @param key   Redis的键
     * @param value 要查询分数的元素
     * @return 元素的分数，若不存在，返回null
     */
    public <T> Double getCacheZSetScore(final String key, final T value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 获取ZSet的元素个数
     *
     * @param key Redis的键
     * @return ZSet的元素个数
     */
    public long getCacheZSetSize(final String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 从ZSet中删除指定元素
     *
     * @param key   Redis的键
     * @param value 要删除的元素
     * @return 删除的元素个数
     */
    public <T> Long deleteCacheZSetValue(final String key, final T value) {
        return redisTemplate.opsForZSet().remove(key, value);
    }

    /**
     * 从ZSet中根据分数删除元素
     *
     * @param key   Redis的键
     * @param min   最小分数
     * @param max   最大分数
     * @return 删除的元素个数
     */
    public Long deleteCacheZSetByScore(final String key, final double min, final double max) {
        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    /**
     * 获取ZSet中排名前N的元素
     *
     * @param key Redis的键
     * @param n   获取的元素个数
     * @return 排名前N的元素
     */
    public <T> Set<T> getTopNFromZSet(final String key, final int n) {
        return redisTemplate.opsForZSet().range(key, 0, n - 1);
    }

    /**
     * 获取ZSet中排名倒数N的元素
     *
     * @param key Redis的键
     * @param n   获取的元素个数
     * @return 排名倒数N的元素
     */
    public <T> Set<T> getBottomNFromZSet(final String key, final int n) {
        return redisTemplate.opsForZSet().range(key, -n, -1);
    }
}
