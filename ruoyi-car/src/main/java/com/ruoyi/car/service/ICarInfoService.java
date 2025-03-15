package com.ruoyi.car.service;

import java.util.List;
import com.ruoyi.car.domain.CarInfo;

/**
 * 车辆信息Service接口
 * 
 * @author ruoyi
 * @date 2025-03-15
 */
public interface ICarInfoService 
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

//    /**
//     * 根据条件分页查询车辆列表
//     *
//     * @param carInfo 车辆信息
//     * @return 车辆信息集合信息
//     */
//    public List<CarInfo> selectUserList(CarInfo carInfo);

    /**
     * 根据车牌号查询车辆信息
     *
     * @param vehicleNo 车牌号
     * @return 车辆信息
     */
    public CarInfo selectCarInfoByVehicleNo(String vehicleNo);

    /**
     * 新增车辆信息，检查车牌号是否包含特定字符串
     *
     * @param carInfo 车辆信息
     * @return 结果
     */
    public int insertCarInfoWithCheck(CarInfo carInfo);


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
     * 批量删除车辆信息
     * 
     * @param vehicleIds 需要删除的车辆信息主键集合
     * @return 结果
     */
    public int deleteCarInfoByVehicleIds(Long[] vehicleIds);

    /**
     * 删除车辆信息信息
     * 
     * @param vehicleId 车辆信息主键
     * @return 结果
     */
    public int deleteCarInfoByVehicleId(Long vehicleId);
}
