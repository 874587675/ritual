package com.ruoyi.project.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.project.business.domain.ObituaryNotice;
import com.ruoyi.project.business.vo.ObituaryNoticeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ObituaryNoticeMapper extends BaseMapper<ObituaryNotice> {
    ObituaryNoticeVO selectObituaryNoticeByUserId(Page<ObituaryNotice> objectPage, @Param("userId") String userId);
}
