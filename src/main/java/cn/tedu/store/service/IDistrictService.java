package cn.tedu.store.service;

import cn.tedu.store.entity.District;

import java.util.List;


/**
 * 处理省市信息的业务层接口
 */
public interface IDistrictService {


    /**
     * 根据parent查询这个省份所有的地理信息
     * @param parent  父级 11010
     * @return 某个省所有的市/区
     */
    List<District> getByParent(String parent);

    /**
     * 根据code查询这个省份所有的地理信息
     * @param code
     * @return
     */
    District getByCode(String code);

}
