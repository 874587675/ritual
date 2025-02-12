package com.ruoyi.framework.redis;

import com.ruoyi.project.business.domain.GiftItem;
import com.ruoyi.project.business.domain.User;
import com.ruoyi.project.business.mapper.UserMapper;
import com.ruoyi.project.business.vo.GiftItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName:ZSetComponent
 * @description:
 * @author: zgc
 **/
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
@Slf4j
public class ZSetComponent {

    private static final String MUSEUM_KEY = "museumId:";
    
    private static final String LEADERBOARD_KEY = "userId:points";
    @Autowired
    public RedisTemplate redisTemplate;
    @Resource
    private UserMapper userMapper;

    /**
     * 用户送礼物增加积分
     *
     * @param userId 发送礼物用户ID
     * @param museumId 纪念馆ID
     * @param points 增加的积分值
     */
    public void sendGift(String userId,Integer museumId ,double points) {
        if (points <= 0) {
            throw new IllegalArgumentException("积分必须为正数");
        }
        String userIdAndRoomId = userId +":"+ museumId;
        String museumKey = MUSEUM_KEY + museumId;
        redisTemplate.opsForZSet().incrementScore(museumKey, userIdAndRoomId, points);
    }

    /**
     * 用户送礼物增加积分
     *
     * @param userId 发送礼物用户ID
     * @param points     增加的积分值
     */
    public void sendGift(String userId,double points) {
        if (points <= 0) {
            throw new IllegalArgumentException("积分必须为正数");
        }
        redisTemplate.opsForZSet().incrementScore(LEADERBOARD_KEY, userId, points);
    }

    /**
     * 获取TOP N排行榜
     *
     * @param topN 前N名
     * @return 当前房间排行榜列表
     */
    public List<GiftItemVO> getTopNByRoomId(int topN,Integer museumId) {
        String museumKey = MUSEUM_KEY + museumId;
        Set<ZSetOperations.TypedTuple<String>> resultSet = redisTemplate.opsForZSet()
                .reverseRangeWithScores(museumKey, 0, topN - 1);

        return convertToUserRankList(resultSet);
    }

    /**
     * 获取TOP N排行榜
     *
     * @param topN 前N名
     * @return 总排行榜列表
     */
    public List<GiftItemVO> getTopN(int topN) {
        Set<ZSetOperations.TypedTuple<String>> resultSet = redisTemplate.opsForZSet()
                .reverseRangeWithScores(LEADERBOARD_KEY, 0, topN - 1);

        return convertToUserRankList(resultSet);
    }

    /**
     * 获取用户排名信息
     *
     * @param userId 用户ID
     * @param museumId 纪念馆ID
     * @return 用户排名对象，不存在时返回null
     */
    public GiftItemVO getUserRank(String userId,Integer museumId) {
        String userIdAndRoomId = userId +":" + museumId;
        String museumKey = MUSEUM_KEY + museumId;
        Double score = redisTemplate.opsForZSet().score(museumKey, userIdAndRoomId);
        if (score == null) return null;
        Long rank = redisTemplate.opsForZSet().reverseRank(museumKey, userIdAndRoomId);

        User user = userMapper.selectById(userId);
        return new GiftItemVO(
                userId,
                user.getNickname(),
                score,
                (rank != null ? rank.intValue() + 1 : -1) // 转换为从1开始的排名
        );
    }

    /**
     * 获取用户排名信息
     *
     * @param userId 用户ID
     * @return 用户排名对象，不存在时返回null
     */
    public GiftItemVO getUserRank(String userId) {
        Double score = redisTemplate.opsForZSet().score(LEADERBOARD_KEY, userId);
        if (score == null) return null;
        Long rank = redisTemplate.opsForZSet().reverseRank(LEADERBOARD_KEY, userId);

        User user = userMapper.selectById(userId);
        return new GiftItemVO(
                userId,
                user.getNickname(),
                score,
                (rank != null ? rank.intValue() + 1 : -1) // 转换为从1开始的排名
        );
    }

    // 转换Redis结果到业务对象
    private List<GiftItemVO> convertToUserRankList(Set<ZSetOperations.TypedTuple<String>> tuples) {
        List<GiftItemVO> rankings = new ArrayList<>();
        int currentRank = 1;
        if (tuples != null) {
            for (ZSetOperations.TypedTuple<String> tuple : tuples) {
                String userId = tuple.getValue();
                User user = userMapper.selectById(userId);
                double score = tuple.getScore() != null ? tuple.getScore() : 0;
                rankings.add(new GiftItemVO(userId, user.getNickname(),score, currentRank++));
            }
        }
        return rankings;
    }
}
