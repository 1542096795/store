package cn.tedu.store.mapper;

import cn.tedu.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IDistrictMapperTest {

    @Autowired
    IDistrictMapper IDistrictMapper;

    @Test
    public void districtMapper() {
        System.err.println("开始测试");
        String parent = "110100";
        List<District> districts = IDistrictMapper.findByParent(parent);
        for (District district : districts) {
            System.err.println(district);
        }
        System.err.println("测试结束");
    }
    @Test
    public void findByCode() {
        System.err.println("开始测试");
        String code = "130828";
        District district = IDistrictMapper.findByCode(code);
        System.err.println(district);
        System.err.println("测试结束");
    }
}
