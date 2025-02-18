package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.project.business.domain.MuseumArticle;
import com.ruoyi.project.business.mapper.MuseumArticleMapper;
import com.ruoyi.project.business.service.MuseumArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class MuseumArticleServiceImpl extends ServiceImpl<MuseumArticleMapper, MuseumArticle> implements MuseumArticleService{
    @Resource
    private MuseumArticleMapper museumArticleMapper;

    @Override
    public IPage<MuseumArticle> selectAllArticleByMuseumId(Integer pageNo, Integer pageSize, Integer museumId) {
        return page(new Page<>(pageNo, pageSize),new LambdaQueryWrapper<MuseumArticle>()
                .eq(MuseumArticle::getMuseumId,museumId).orderByDesc(MuseumArticle::getUpdateTime));
    }

    @Override
    @Transactional
    public String addArticleByMuseumId(MuseumArticle museumArticle) {
        try{
            int insertResult = museumArticleMapper.insert(museumArticle);
            if (insertResult<=0){
                log.error("添加追忆文章失败, 馆藏ID:{}", museumArticle.getMuseumId());
                throw new ServiceException("插入追忆文章失败");
            }else{
                log.info("添加追忆文章成功");
                return "添加追忆文章成功";
            }
        }catch (Exception e){
            log.error("添加追忆文章失败, 馆藏ID:{}", museumArticle.getMuseumId());
            throw new ServiceException("插入追忆文章失败");
        }
    }
}
