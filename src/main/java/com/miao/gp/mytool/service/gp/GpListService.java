package com.miao.gp.mytool.service.gp;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class GpListService {
    @Resource
    private GpListRepository gpListRepository;
    public GpList query(String code) {
        GpList gpList=new GpList();
        gpList.setCode(code);
        Optional<GpList> one = gpListRepository.findOne( Example.of(gpList));
        return one.get();
    }
    public List<GpList> queryAll() {
        return gpListRepository.findAll();
    }

}
