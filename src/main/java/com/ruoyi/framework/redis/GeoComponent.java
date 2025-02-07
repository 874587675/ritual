package com.ruoyi.framework.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zgc
 */
@SuppressWarnings(value = { "unchecked", "rawtypes" })
@Component
public class GeoComponent {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加成员
     * @param key
     * @param longitude 经度
     * @param latitude 纬度
     * @param member 成员
     * @return 添加成功的成员数量
     */
    public Long geoAdd(String key, double longitude, double latitude, String member){
        return redisTemplate.opsForGeo().add(key, new Point(longitude, latitude), member);
    }

    /**
     * 批量添加多个地理位置成员
     * @param key Redis中的键
     * @param memberCoordinateMap 包含多个成员和经纬度的映射
     * @return 添加成功的成员数量
     */
    public Long addGeoLocations(String key, Map<String, Point> memberCoordinateMap) {
        // 获取GeoOperations对象
        GeoOperations<String, String> geoOps = redisTemplate.opsForGeo();
        // 批量添加成员
        return geoOps.add(key, memberCoordinateMap);
    }

    /**
     * 获取两个成员的距离
     * @param key
     * @param member1 成员1
     * @param member2 成员2
     * @return Distance
     */
    public Distance geoDist(String key, String member1, String member2){
        return redisTemplate.opsForGeo().distance(key, member1, member2);
    }
    /**
     * 获取两个成员的距离(距离单位)
     * @param key
     * @param member1
     * @param member2
     * @param metric 度规（枚举）（km、m）
     * @return Distance
     */
    public Distance geoDist(String key, String member1, String member2, Metrics metric){
        return redisTemplate.opsForGeo().distance(key, member1, member2, metric);
    }
    /**
     * 获取成员经纬度
     * @param key
     * @param members
     * @return
     */
    public List<Point> geoPos(String key, String... members){
        return redisTemplate.opsForGeo().position(key, members);
    }
    /**
     * 获取某个成员附近（距离范围内）的成员
     * @param key
     * @param member 成员
     * @param v 距离
     * @param metric  度规（枚举）（km、m）
     * @return
     */
    public List<String> geoRadiusByMember(String key, String member, double v, Metrics metric){
        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults = redisTemplate.opsForGeo().radius(key, member, new Distance(v, metric));
        List<String> result = new ArrayList<>();
        if (geoResults != null) {
            for(GeoResult<RedisGeoCommands.GeoLocation<String>> geoResult : geoResults.getContent()){
                result.add(geoResult.getContent().getName());
            }
        }
        return result;
    }
    /**
     * 获取某个成员附近（距离范围内）的成员
     * @param key
     * @param member 成员
     * @param v 距离
     * @param metric  度规（枚举）（km、m）
     * @param args
     * arg使用示例：
     * RedisGeoCommands.GeoRadiusCommandArgs args =
     * RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
     * .includeCoordinates()
     * .includeDistance()
     * .limit(1)
     * .sortAscending(); 
     * 
     * includeCoordinates：结果包含坐标，includeDistance：结果包含距离，limit：返回数量：sort...：排序
     * @return GeoResults
     * geoResult.getContent().getName() 元素名称
     * geoResult.getContent().getPoint() 元素坐标
     * geoResult.getDistance() 元素距离
     */
    public GeoResults<RedisGeoCommands.GeoLocation<String>> geoRadiusByMember(String key, String member, double v, Metrics metric, RedisGeoCommands.GeoRadiusCommandArgs args){
        return redisTemplate.opsForGeo().radius(key, member, new Distance(v, metric), args);
    }
    
    
}
