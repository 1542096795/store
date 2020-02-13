package cn.tedu.store.service.impl;

import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.IDistrictMapper;
import cn.tedu.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 处理省/市/区数据的业务类
 */
@Service
public class DistrictServiceImpl implements IDistrictService {


    @Autowired
    private IDistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        System.err.println("开始处理省市区的业务层");
        List<District> districts = districtMapper.findByParent(parent);
        return districts;
    }

    @Override
    public District getByCode(String code) {
        return districtMapper.findByCode(code);
    }
}
