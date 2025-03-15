package com.ruoyi.car.mapper;

import java.util.List;
import com.ruoyi.car.domain.CarInfo;

/**
 * 车辆信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-15
 */
public interface CarInfoMapper 
{
    /**
     * 查询车辆信息
     * 
     * @param vehicleId 车辆信息主键
     * @return 车辆信息
     */
    public CarInfo selectCarInfoByVehicleId(Long vehicleId);

    /**
     * 查询车辆信息列表
     * 
     * @param carInfo 车辆信息
     * @return 车辆信息集合
     */
    public List<CarInfo> selectCarInfoList(CarInfo carInfo);

    /**
     * 新增车辆信息
     * 
     * @param carInfo 车辆信息
     * @return 结果
     */
    public int insertCarInfo(CarInfo carInfo);

    /**
     * 修改车辆信息
     * 
     * @param carInfo 车辆信息
     * @return 结果
     */
    public int updateCarInfo(CarInfo carInfo);

    /**
     * 删除车辆信息
     * 
     * @param vehicleId 车辆信息主键
     * @return 结果
     */
    public int deleteCarInfoByVehicleId(Long vehicleId);

    /**
     * 批量删除车辆信息
     * 
     * @param vehicleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCarInfoByVehicleIds(Long[] vehicleIds);
}
