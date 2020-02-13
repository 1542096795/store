package cn.tedu.store.mapper;

import cn.tedu.store.entity.District;

import java.util.List;

/**
 * 处理省市信息的持久层接口
 */
public interface IDistrictMapper {

    /**
     * 根据父级的行政代号获取全国所有的省/某省所有的市/某市所有的区的列表
     * @param parent 父级的行政代号，如果需要获取全国所有的省，则使用"86"
     * @return 全国所有的省/某省所有的市/某市所有的区的列表
     */
    List<District> findByParent(String  parent);


    /**
     * 根据省/市/区的行政代号获取详情
     * @param code 省/市/区的行政代号
     * @return 匹配的省/市/区的详情，如果没有匹配的数据，则返回null
     */
    District findByCode(String code);
}
