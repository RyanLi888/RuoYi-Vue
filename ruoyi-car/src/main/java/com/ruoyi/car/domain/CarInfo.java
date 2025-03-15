package com.ruoyi.car.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车辆信息对象 car_info
 * 
 * @author ruoyi
 * @date 2025-03-15
 */
public class CarInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 车辆ID */
    @Excel(name = "车辆ID")
    private Long vehicleId;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String vehicleNo;

    /** 车辆颜色 */
    @Excel(name = "车辆颜色")
    private String vehicleColor;

    /** 车辆类型 */
    @Excel(name = "车辆类型")
    private String vehicleType;

    /** 手机号 */
    @Excel(name = "手机号")
    private String sim;

    /** 部门id */
    private Long deptId;

    /** 车辆所属部门 */
    @Excel(name = "车辆所属部门")
    private String deptName;

    /** 所属车组 */
    @Excel(name = "所属车组")
    private String groupInfo;

    /** 通道能使用的通道号列表 */
    private String chnEnable;

    /** 是否删除标识 */
    @Excel(name = "是否删除标识")
    private Long deleteFlag;

    /** 联网状态 */
    @Excel(name = "联网状态")
    private Long onlineStatus;

    /** 最近登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date loginTime;

    /** 车辆里程 */
    @Excel(name = "车辆里程")
    private Long totalMaintenance;

    /** 保养情况 */
    @Excel(name = "保养情况")
    private Long maintenance;

    public void setVehicleId(Long vehicleId) 
    {
        this.vehicleId = vehicleId;
    }

    public Long getVehicleId() 
    {
        return vehicleId;
    }

    public void setVehicleNo(String vehicleNo) 
    {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleNo() 
    {
        return vehicleNo;
    }

    public void setVehicleColor(String vehicleColor) 
    {
        this.vehicleColor = vehicleColor;
    }

    public String getVehicleColor() 
    {
        return vehicleColor;
    }

    public void setVehicleType(String vehicleType) 
    {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() 
    {
        return vehicleType;
    }

    public void setSim(String sim) 
    {
        this.sim = sim;
    }

    public String getSim() 
    {
        return sim;
    }

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }

    public void setGroupInfo(String groupInfo) 
    {
        this.groupInfo = groupInfo;
    }

    public String getGroupInfo() 
    {
        return groupInfo;
    }

    public void setChnEnable(String chnEnable) 
    {
        this.chnEnable = chnEnable;
    }

    public String getChnEnable() 
    {
        return chnEnable;
    }

    public void setDeleteFlag(Long deleteFlag) 
    {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag() 
    {
        return deleteFlag;
    }

    public void setOnlineStatus(Long onlineStatus) 
    {
        this.onlineStatus = onlineStatus;
    }

    public Long getOnlineStatus() 
    {
        return onlineStatus;
    }

    public void setLoginTime(Date loginTime) 
    {
        this.loginTime = loginTime;
    }

    public Date getLoginTime() 
    {
        return loginTime;
    }

    public void setTotalMaintenance(Long totalMaintenance) 
    {
        this.totalMaintenance = totalMaintenance;
    }

    public Long getTotalMaintenance() 
    {
        return totalMaintenance;
    }

    public void setMaintenance(Long maintenance) 
    {
        this.maintenance = maintenance;
    }

    public Long getMaintenance() 
    {
        return maintenance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("vehicleId", getVehicleId())
            .append("vehicleNo", getVehicleNo())
            .append("vehicleColor", getVehicleColor())
            .append("vehicleType", getVehicleType())
            .append("sim", getSim())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("groupInfo", getGroupInfo())
            .append("chnEnable", getChnEnable())
            .append("deleteFlag", getDeleteFlag())
            .append("onlineStatus", getOnlineStatus())
            .append("loginTime", getLoginTime())
            .append("totalMaintenance", getTotalMaintenance())
            .append("maintenance", getMaintenance())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
