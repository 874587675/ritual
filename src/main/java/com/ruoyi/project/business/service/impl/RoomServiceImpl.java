package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.project.business.constant.RoleEnum;
import com.ruoyi.project.business.constant.RoomEnum;
import com.ruoyi.project.business.domain.Room;
import com.ruoyi.project.business.domain.RoomBack;
import com.ruoyi.project.business.domain.RoomMembers;
import com.ruoyi.project.business.mapper.RoomBackMapper;
import com.ruoyi.project.business.mapper.RoomMapper;
import com.ruoyi.project.business.mapper.RoomMembersMapper;
import com.ruoyi.project.business.service.RoomService;
import com.ruoyi.project.business.vo.RoomVO;
import com.ruoyi.project.business.websocket.ChatWebSocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {

    @Resource
    private RoomMapper roomMapper;

    @Resource
    private RoomBackMapper roomBackMapper;
    
    @Resource
    private RoomMembersMapper roomMembersMapper;
    

    @Override
    @Transactional
    public RoomVO createRoom(RoomVO roomVO) {
        try {
            if (roomVO != null) {
                roomVO.setRoomStatus(RoomEnum.OPEN.getCode());
                roomMapper.insert(roomVO);
                Integer roomId = roomVO.getId();
                roomVO.setId(roomId);
                log.info("创建房间成功");
                //创建房间成员对象
                RoomMembers roomMember = RoomMembers.builder()
                        .roomId(roomId)
                        .userId(roomVO.getUserId())
                        .role(RoleEnum.OWNER.getCode())
                        .build();
                roomMembersMapper.insert(roomMember);
                log.info("创建房间房主成功");
                return roomVO;
            } else {
                log.error("创建房间失败");
                throw new ServiceException("创建房间失败");
            }
        } catch (Exception e) {
            log.error("创建房间失败", e);
            throw new ServiceException("创建房间失败");
        }
    }

    @Override
    public String closeRoom(Integer id) {
        try{
            if (id != null) {
                int closeResult = roomMapper.update(new LambdaUpdateWrapper<Room>()
                        .eq(Room::getId, id)
                        .set(Room::getRoomStatus, RoomEnum.CLOSE.getCode()));
                if (closeResult<=0){
                    log.error("关闭房间失败");
                    throw new ServiceException("关闭房间失败");
                }
                //关闭所有用户连接
                ChatWebSocketHandler.disconnectAllUsers();
                log.info("关闭房间成功");
                return "关闭房间成功";
            }else {
                log.error("关闭房间失败，房间ID为空");
                throw new ServiceException("关闭房间失败，房间ID为空");
            }
        }catch (Exception e){
            log.error("关闭房间失败",e);
            throw new ServiceException("关闭房间失败");
        }
    }

    @Override
    @Transactional
    public String changeRoomBack(RoomBack roomBack) {
        try{
            if (roomBack != null) {
                roomBackMapper.updateById(roomBack);
                log.info("更换房间背景成功");
                return "更换房间背景成功";
            }else {
                log.error("更换房间背景参数为空，更换失败");
                throw new ServiceException("更换房间背景参数为空，更换失败");
            }
        }catch (Exception e){
            log.error("更换房间背景失败",e);
            throw new ServiceException("更换房间背景失败");
        }
    }

    @Override
    public IPage<RoomVO> myRoom(Integer pageNo, Integer pageSize, String userId) {
        return roomMapper.myRoom(new Page<>(pageNo,pageSize),userId);
    }
}
