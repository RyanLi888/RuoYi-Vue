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
    @Override
    public CarInfo selectCarInfoByVehicleNo(String vehicleNo) {
        return carInfoMapper.selectCarInfoByVehicleNo(vehicleNo);
    }

    @Override
    public int insertCarInfoIfNotExists(CarInfo carInfo) {
        CarInfo existingCar = selectCarInfoByVehicleNo(carInfo.getVehicleNo());
        if (existingCar == null) {
            carInfo.setCreateTime(DateUtils.getNowDate());
            return carInfoMapper.insertCarInfo(carInfo);
        }
        return 0; // 车牌号已存在，不插入
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
