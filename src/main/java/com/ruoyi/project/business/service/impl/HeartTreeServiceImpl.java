package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.HeartTree;
import com.ruoyi.project.business.domain.Message;
import com.ruoyi.project.business.domain.MuseumInfo;
import com.ruoyi.project.business.domain.User;
import com.ruoyi.project.business.mapper.HeartTreeMapper;
import com.ruoyi.project.business.mapper.MessageMapper;
import com.ruoyi.project.business.mapper.MuseumInfoMapper;
import com.ruoyi.project.business.mapper.UserMapper;
import com.ruoyi.project.business.service.HeartTreeService;
import com.ruoyi.project.business.vo.HeartTreeVO;
import com.ruoyi.project.business.vo.MessageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class HeartTreeServiceImpl extends ServiceImpl<HeartTreeMapper, HeartTree> implements HeartTreeService {

    @Resource
    private HeartTreeMapper heartTreeMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private MuseumInfoMapper museumInfoMapper;
    @Resource
    private MessageMapper messageMapper;

    @Override
    public IPage<HeartTree> selectAllHeartTree(Integer pageNo, Integer pageSize) {
        return page(new Page<>(pageNo, pageSize));
    }

    @Override
    public String addHeartTree(HeartTreeVO heartTreeVO) {
        try {
            User user = userMapper.selectById(heartTreeVO.getUserId());
            if (user == null) {
                log.error("用户不存在,用户编号{}", heartTreeVO.getUserId());
                throw new RuntimeException("用户不存在");
            }
            //初始化参数
            heartTreeVO.setAvatar(user.getAvatar());
            heartTreeVO.setNickName(user.getNickname());

            MuseumInfo museumInfo = museumInfoMapper.selectById(heartTreeVO.getMuseumId());
            heartTreeVO.setMuseumName(museumInfo.getName());
            heartTreeVO.setLikeCount(0);
            heartTreeVO.setMessageCount(0);
        
            //将HeartTreeVO 转换成 HeartTree
            HeartTree heartTree = new HeartTree();
            BeanUtils.copyProperties(heartTreeVO,heartTree);
            
            int insertResult = heartTreeMapper.insert(heartTree);
            if (insertResult <= 0) {
                log.error("添加心灵树洞失败, 用户ID:{}", heartTreeVO.getUserId());
                throw new RuntimeException("添加心灵树洞失败");
            }
            log.info("添加心灵树洞成功");
            return "添加心灵树洞成功";
        } catch (Exception e) {
            log.error("添加心灵树洞失败, 错误原因:{}", e.getMessage());
            throw new RuntimeException("添加心灵树洞失败");
        }
    }

    @Override
    public HeartTree selectHeartTreeDetails(Integer treeId) {
        HeartTree heartTree = heartTreeMapper.selectById(treeId);
        if (heartTree == null) {
            log.error("该心灵树洞不存在, 树洞ID:{}", treeId);
            throw new RuntimeException("该心灵树洞不存在");
        }
        log.info("查询心灵树洞详情成功，{}",heartTree);
        return heartTree;
    }

    @Override
    public IPage<Message> selectTopMessages(Integer pageNo, Integer pageSize, Integer treeId) {
        return messageMapper.selectPage(new Page<>(pageNo,pageSize),new LambdaQueryWrapper<Message>().eq(Message::getTreeId, treeId).orderByDesc(Message::getCreateTime));
    }

    @Override
    public IPage<MessageVO> getSubMessages(Integer pageNo, Integer pageSize, Integer topId) {
        return messageMapper.getSubMessages(new Page<>(pageNo, pageSize),topId);
    }

    @Override
    @Transactional
    public String deleteHeartTree(Integer treeId) {
        try{
            HeartTree heartTree = heartTreeMapper.selectOne(new LambdaQueryWrapper<HeartTree>().eq(HeartTree::getId,treeId).eq(HeartTree::getIsDeleted,0));
            if (heartTree == null) {
                log.error("该心灵树洞不存在, 树洞ID:{}", treeId);
                throw new RuntimeException("该心灵树洞不存在");
            }
            int deleteResult = heartTreeMapper.update(new LambdaUpdateWrapper<HeartTree>().eq(HeartTree::getId,treeId).set(HeartTree::getIsDeleted,1));
            if (deleteResult <= 0) {
                log.error("删除心心灵树洞失败, 树洞ID:{}", treeId);
                throw new RuntimeException("删除心灵树洞失败");
            }
            log.info("删除心灵树洞成功");
            return "删除心灵树洞成功";
        }catch(Exception e){
            log.error("删除心灵树洞失败, 错误原因:{}", e.getMessage());
            throw new RuntimeException("删除心灵树洞失败");
        }
    }
}
