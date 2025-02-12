package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.project.business.domain.MuseumArticle;
import com.baomidou.mybatisplus.extension.service.IService;
public interface MuseumArticleService extends IService<MuseumArticle>{
    
    IPage<MuseumArticle> selectAllArticleByMuseumId(Integer pageNo, Integer pageSize, Integer museumId);

    String addArticleByMuseumId(MuseumArticle museumArticle);
}
