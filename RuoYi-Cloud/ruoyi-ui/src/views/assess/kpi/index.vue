<template>
  <div class="app-container">

<el-row>
  <el-col :span="4" :xs="24">
    <div class="head-container">
          <el-input
            v-model="deptName"
            placeholder="请输入指标名称"
            clearable
            size="small"
            prefix-icon="el-icon-search"
            style="margin-bottom: 20px"
          />
   </div>
 <el-table
      :data="tableData"
      style="width: 100%">

      <el-table-column
        prop="name"
        label="指标"
        width="">
      </el-table-column>
  
    </el-table>

  </el-col>
  
  <el-col :span="20">
  
  
  </el-col>

</el-row>



  </div>
</template>

<script>
import { listKpi, getKpi, delKpi, addKpi, updateKpi } from "@/api/assess/kpi";
 
export default {
  name: "Kpi",
  components: {
  },
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
      // 指标库表格数据
      kpiList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        kpiName: null,
        kpiDescription: null,
        kpiGrading: null,
        kpiWeight: null,
        kpiParentId: null,
        kpiType: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
       tableData: [{
            date: '2016-05-02',
            name: '王小1虎',
            address: '上海市普陀区金沙江路 1518 弄'
          }, {
            date: '2016-05-04',
            name: '王小2虎',
            address: '上海市普陀区金沙江路 1517 弄'
          }, {
            date: '2016-05-01',
            name: '王小3虎',
            address: '上海市普陀区金沙江路 1519 弄'
          }, {
            date: '2016-05-03',
            name: '王小4虎',
            address: '上海市普陀区金沙江路 1516 弄'
          }]
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询指标库列表 */
    getList() {
      this.loading = true;
      listKpi(this.queryParams).then(response => {
        this.kpiList = response.rows;
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
        kpiId: null,
        kpiName: null,
        kpiDescription: null,
        kpiGrading: null,
        kpiWeight: null,
        kpiParentId: null,
        kpiType: null
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
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.kpiId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加指标库";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const kpiId = row.kpiId || this.ids
      getKpi(kpiId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改指标库";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.kpiId != null) {
            updateKpi(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addKpi(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const kpiIds = row.kpiId || this.ids;
      this.$confirm('是否确认删除指标库编号为"' + kpiIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delKpi(kpiIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('assess/kpi/export', {
        ...this.queryParams
      }, `assess_kpi.xlsx`)
    }
  }
};
</script>