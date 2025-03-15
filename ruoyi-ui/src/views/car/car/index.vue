<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车牌号" prop="vehicleNo">
        <el-input
          v-model="queryParams.vehicleNo"
          placeholder="请输入车牌号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车辆颜色" prop="vehicleColor">
        <el-input
          v-model="queryParams.vehicleColor"
          placeholder="请输入车辆颜色"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车辆类型" prop="vehicleType">
        <el-select v-model="queryParams.vehicleType" placeholder="请选择车辆类型" clearable>
          <el-option
            v-for="dict in dict.type.sys_car_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="手机号" prop="sim">
        <el-input
          v-model="queryParams.sim"
          placeholder="请输入手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车辆所属部门" prop="deptName">
        <el-input
          v-model="queryParams.deptName"
          placeholder="请输入车辆所属部门"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['car:car:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['car:car:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['car:car:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['car:car:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="carList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="车辆ID" align="center" prop="vehicleId" />
      <el-table-column label="车牌号" align="center" prop="vehicleNo" />
      <el-table-column label="车辆颜色" align="center" prop="vehicleColor" />
      <el-table-column label="车辆类型" align="center" prop="vehicleType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_car_type" :value="scope.row.vehicleType"/>
        </template>
      </el-table-column>
      <el-table-column label="手机号" align="center" prop="sim" />
      <el-table-column label="车辆所属部门" align="center" prop="deptName" />
      <el-table-column label="所属车组" align="center" prop="groupInfo" />
      <el-table-column label="是否删除标识" align="center" prop="deleteFlag" />
      <el-table-column label="联网状态" align="center" prop="onlineStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_car_status" :value="scope.row.onlineStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="最近登录时间" align="center" prop="loginTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.loginTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="车辆里程" align="center" prop="totalMaintenance" />
      <el-table-column label="保养情况" align="center" prop="maintenance">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_car_maintenance" :value="scope.row.maintenance"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['car:car:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['car:car:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改车辆信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车牌号" prop="vehicleNo">
          <el-input v-model="form.vehicleNo" placeholder="请输入车牌号" />
        </el-form-item>
        <el-form-item label="车辆颜色" prop="vehicleColor">
          <el-input v-model="form.vehicleColor" placeholder="请输入车辆颜色" />
        </el-form-item>
        <el-form-item label="车辆类型" prop="vehicleType">
          <el-select v-model="form.vehicleType" placeholder="请选择车辆类型">
            <el-option
              v-for="dict in dict.type.sys_car_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="手机号" prop="sim">
          <el-input v-model="form.sim" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="部门id" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入部门id" />
        </el-form-item>
        <el-form-item label="车辆所属部门" prop="deptName">
          <el-input v-model="form.deptName" placeholder="请输入车辆所属部门" />
        </el-form-item>
        <el-form-item label="所属车组" prop="groupInfo">
          <el-input v-model="form.groupInfo" placeholder="请输入所属车组" />
        </el-form-item>
        <el-form-item label="联网状态" prop="onlineStatus">
          <el-select v-model="form.onlineStatus" placeholder="请选择联网状态">
            <el-option
              v-for="dict in dict.type.sys_car_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="最近登录时间" prop="loginTime">
          <el-date-picker clearable
            v-model="form.loginTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择最近登录时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="车辆里程" prop="totalMaintenance">
          <el-input v-model="form.totalMaintenance" placeholder="请输入车辆里程" />
        </el-form-item>
        <el-form-item label="保养情况" prop="maintenance">
          <el-radio-group v-model="form.maintenance">
            <el-radio
              v-for="dict in dict.type.sys_car_maintenance"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCar, getCar, delCar, addCar, updateCar } from "@/api/car/car";

export default {
  name: "Car",
  dicts: ['sys_car_status', 'sys_car_maintenance', 'sys_car_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 车辆信息表格数据
      carList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 保养情况时间范围
      daterangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vehicleNo: null,
        vehicleColor: null,
        vehicleType: null,
        sim: null,
        deptName: null,
        createTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        vehicleNo: [
          { required: true, message: "车牌号不能为空", trigger: "blur" }
        ],
        vehicleColor: [
          { required: true, message: "车辆颜色不能为空", trigger: "blur" }
        ],
        vehicleType: [
          { required: true, message: "车辆类型不能为空", trigger: "change" }
        ],
        sim: [
          { required: true, message: "手机号不能为空", trigger: "blur" }
        ],
        deptName: [
          { required: true, message: "车辆所属部门不能为空", trigger: "blur" }
        ],
        totalMaintenance: [
          { required: true, message: "车辆里程不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询车辆信息列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      listCar(this.queryParams).then(response => {
        this.carList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        vehicleId: null,
        vehicleNo: null,
        vehicleColor: null,
        vehicleType: null,
        sim: null,
        deptId: null,
        deptName: null,
        groupInfo: null,
        chnEnable: null,
        deleteFlag: null,
        onlineStatus: null,
        loginTime: null,
        totalMaintenance: null,
        maintenance: null,
        createTime: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.vehicleId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加车辆信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const vehicleId = row.vehicleId || this.ids
      getCar(vehicleId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车辆信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.vehicleId != null) {
            updateCar(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCar(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const vehicleIds = row.vehicleId || this.ids;
      this.$modal.confirm('是否确认删除车辆信息编号为"' + vehicleIds + '"的数据项？').then(function() {
        return delCar(vehicleIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('car/car/export', {
        ...this.queryParams
      }, `car_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
