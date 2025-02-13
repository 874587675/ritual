package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.project.business.constant.RoleEnum;
import com.ruoyi.project.business.domain.RoomMembers;
import com.ruoyi.project.business.mapper.RoomMembersMapper;
import com.ruoyi.project.business.service.RoomMembersService;
import com.ruoyi.project.business.vo.RoomMembersVO;
import com.ruoyi.project.business.websocket.ChatWebSocketHandler;
import com.ruoyi.project.business.websocket.WebSocketConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class RoomMembersServiceImpl extends ServiceImpl<RoomMembersMapper, RoomMembers> implements RoomMembersService {
    @Resource
    private RoomMembersMapper roomMembersMapper;


    @Override
    public IPage<RoomMembers> getRoomMembers(Integer pageNo, Integer pageSize, Integer roomId) {
        return page(new Page<>(pageNo, pageSize), new LambdaQueryWrapper<RoomMembers>()
                .eq(RoomMembers::getRoomId, roomId)
                .orderByDesc(RoomMembers::getGiftScore));
    }

    @Override
    @Transactional
    public String addRoomMembers(RoomMembersVO roomMembersVO) {
        //添加房间成员
        try {
            //初始化成员信息
            roomMembersVO.setRole(RoleEnum.MEMBER.getCode());
            roomMembersVO.setGiftScore(0);
            roomMembersMapper.insert(roomMembersVO);
            log.info("添加房间成员成功");
            return "添加房间成员成功";
        } catch (Exception e) {
            log.error("添加房间成员失败", e);
            return "添加房间成员失败";
        }
    }

    @Override
    @Transactional
    public String deleteRoomMembers(Integer id) {
        //逻辑删除房间成员
        try {
            int updateResult = roomMembersMapper.update(new LambdaUpdateWrapper<RoomMembers>()
                    .eq(RoomMembers::getId, id)
                    .set(RoomMembers::getIsDeleted, 1));
            if (updateResult > 0) {
                //查询房间成员信息
                RoomMembers roomMembers = roomMembersMapper.selectById(id);
                //删除房间成员成功后，断开 WebSocket 连接
                ChatWebSocketHandler.disconnectUser(roomMembers.getUserId());
                log.info("删除房间成员成功");
                return "删除房间成员成功";
            } else {
                log.error("删除房间成员失败");
                throw new ServiceException("删除房间成员失败");
            }
        } catch (Exception e) {
            log.error("删除房间成员失败", e);
            throw new ServiceException("删除房间成员失败");
        }
    }
}
