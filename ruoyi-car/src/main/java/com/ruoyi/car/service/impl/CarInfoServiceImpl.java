package com.ruoyi.car.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.car.mapper.CarInfoMapper;
import com.ruoyi.car.domain.CarInfo;
import com.ruoyi.car.service.ICarInfoService;

/**
 * 车辆信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-15
 */
@Service
public class CarInfoServiceImpl implements ICarInfoService 
{
    @Autowired
    private CarInfoMapper carInfoMapper;

    /**
     * 查询车辆信息
     * 
     * @param vehicleId 车辆信息主键
     * @return 车辆信息
     */
    @Override
    public CarInfo selectCarInfoByVehicleId(Long vehicleId)
    {
        return carInfoMapper.selectCarInfoByVehicleId(vehicleId);
    }

    /**
     * 查询车辆信息列表
     * 
     * @param carInfo 车辆信息
     * @return 车辆信息
     */
    @Override
    public List<CarInfo> selectCarInfoList(CarInfo carInfo)
    {
        return carInfoMapper.selectCarInfoList(carInfo);
    }

    /**
     * 根据车牌号查询车辆信息
     *
     * @param vehicleNo 车牌号
     * @return
     */
    @Override
    public CarInfo selectCarInfoByVehicleNo(String vehicleNo) {
        return carInfoMapper.selectCarInfoByVehicleNo(vehicleNo);
    }

    /**
     *      检查车牌号是否存在，存在不插入。
     * @param carInfo 车辆信息
     * @return
     */
    @Override
    public int insertCarInfoWithCheck(CarInfo carInfo) {
        String vehicleNo = carInfo.getVehicleNo();
        // 定义不允许添加的车牌号包含的字符串
        String[] restrictedStrings = {"MT001", "GN351", "Ht67"};
        for (String restricted : restrictedStrings) {
            if (vehicleNo != null && vehicleNo.contains(restricted)) {
                return 0; // 车牌号包含不允许的字符串，不插入
            }
        }
        // 检查车牌号是否已存在
        CarInfo existingCar = selectCarInfoByVehicleNo(vehicleNo);
        if (existingCar != null) {
            return 0; // 车牌号已存在，不插入
        }
        carInfo.setCreateTime(DateUtils.getNowDate());
        return carInfoMapper.insertCarInfo(carInfo);
    }


    /**
     * 新增车辆信息
     * 
     * @param carInfo 车辆信息
     * @return 结果
     */
    @Override
    public int insertCarInfo(CarInfo carInfo)
    {
        carInfo.setCreateTime(DateUtils.getNowDate());
        return carInfoMapper.insertCarInfo(carInfo);
    }

    /**
     * 修改车辆信息
     * 
     * @param carInfo 车辆信息
     * @return 结果
     */
    @Override
    public int updateCarInfo(CarInfo carInfo)
    {
        carInfo.setUpdateTime(DateUtils.getNowDate());
        return carInfoMapper.updateCarInfo(carInfo);
    }

    /**
     * 批量删除车辆信息
     * 
     * @param vehicleIds 需要删除的车辆信息主键
     * @return 结果
     */
    @Override
    public int deleteCarInfoByVehicleIds(Long[] vehicleIds)
    {
        return carInfoMapper.deleteCarInfoByVehicleIds(vehicleIds);
    }

    /**
     * 删除车辆信息信息
     * 
     * @param vehicleId 车辆信息主键
     * @return 结果
     */
    @Override
    public int deleteCarInfoByVehicleId(Long vehicleId)
    {
        return carInfoMapper.deleteCarInfoByVehicleId(vehicleId);
    }
}
