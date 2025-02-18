package com.ruoyi.project.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.project.business.domain.UserTeam;
import com.ruoyi.project.business.vo.UserTeamVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserTeamMapper extends BaseMapper<UserTeam> {

    UserTeamVO selectForIncomeAndInviteUserCount(@Param("userId") String userId);

    IPage<UserTeamVO> selectUserTeam(Page<UserTeam> objectPage,@Param("userId") String userId);
}
