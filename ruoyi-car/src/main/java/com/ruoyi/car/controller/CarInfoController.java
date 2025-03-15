package com.ruoyi.car.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.car.domain.CarInfo;
import com.ruoyi.car.service.ICarInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车辆信息Controller
 * 
 * @author ruoyi
 * @date 2025-03-15
 */
@RestController
@RequestMapping("/car/car")
public class CarInfoController extends BaseController
{
    @Autowired
    private ICarInfoService carInfoService;

    /**
     * 查询车辆信息列表
     */
    @PreAuthorize("@ss.hasPermi('car:car:list')")
    @GetMapping("/list")
    public TableDataInfo list(CarInfo carInfo)
    {
        startPage();
        List<CarInfo> list = carInfoService.selectCarInfoList(carInfo);
        return getDataTable(list);
    }

    /**
     * 导出车辆信息列表
     */
    @PreAuthorize("@ss.hasPermi('car:car:export')")
    @Log(title = "车辆信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CarInfo carInfo)
    {
        List<CarInfo> list = carInfoService.selectCarInfoList(carInfo);
        ExcelUtil<CarInfo> util = new ExcelUtil<CarInfo>(CarInfo.class);
        util.exportExcel(response, list, "车辆信息数据");
    }

    /**
     * 获取车辆信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('car:car:query')")
    @GetMapping(value = "/{vehicleId}")
    public AjaxResult getInfo(@PathVariable("vehicleId") Long vehicleId)
    {
        return success(carInfoService.selectCarInfoByVehicleId(vehicleId));
    }

    /**
     * 新增车辆信息
     */
    @PreAuthorize("@ss.hasPermi('car:car:add')")
    @Log(title = "车辆信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CarInfo carInfo)
    {
        int result = carInfoService.insertCarInfoWithCheck(carInfo);
        if (result > 0) {
            return toAjax(result);
        } else {
            return error("车牌号不允许添加或已存在");
        }
    }

    /**
     * 修改车辆信息
     */
    @PreAuthorize("@ss.hasPermi('car:car:edit')")
    @Log(title = "车辆信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CarInfo carInfo)
    {
        return toAjax(carInfoService.updateCarInfo(carInfo));
    }

    /**
     * 删除车辆信息
     */
    @PreAuthorize("@ss.hasPermi('car:car:remove')")
    @Log(title = "车辆信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{vehicleIds}")
    public AjaxResult remove(@PathVariable Long[] vehicleIds)
    {
        return toAjax(carInfoService.deleteCarInfoByVehicleIds(vehicleIds));
    }
}
