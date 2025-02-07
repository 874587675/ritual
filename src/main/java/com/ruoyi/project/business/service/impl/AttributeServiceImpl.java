package com.ruoyi.project.business.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.mapper.AttributeMapper;
import com.ruoyi.project.business.domain.Attribute;
import com.ruoyi.project.business.service.AttributeService;
@Service
public class AttributeServiceImpl extends ServiceImpl<AttributeMapper, Attribute> implements AttributeService{

}
