<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="指标名称" prop="kpiName">
        <el-input
          v-model="queryParams.kpiName"
          placeholder="请输入指标名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="指标分数" prop="kpiGrading">
        <el-input
          v-model="queryParams.kpiGrading"
          placeholder="请输入指标分数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="指标权重" prop="kpiWeight">
        <el-input
          v-model="queryParams.kpiWeight"
          placeholder="请输入指标权重"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="父级指标" prop="kpiParentId">
        <el-input
          v-model="queryParams.kpiParentId"
          placeholder="请输入父级指标"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="0:定量 1：定性" prop="kpiType">
        <el-select v-model="queryParams.kpiType" placeholder="请选择0:定量 1：定性" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
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
          v-hasPermi="['assess:kpi:add']"
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
          v-hasPermi="['assess:kpi:edit']"
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
          v-hasPermi="['assess:kpi:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['assess:kpi:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="kpiList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="指标ID" align="center" prop="kpiId" />
      <el-table-column label="指标名称" align="center" prop="kpiName" />
      <el-table-column label="指标描述" align="center" prop="kpiDescription" />
      <el-table-column label="指标分数" align="center" prop="kpiGrading" />
      <el-table-column label="指标权重" align="center" prop="kpiWeight" />
      <el-table-column label="父级指标" align="center" prop="kpiParentId" />
      <el-table-column label="0:定量 1：定性" align="center" prop="kpiType" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['assess:kpi:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['assess:kpi:remove']"
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

    <!-- 添加或修改指标库对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="指标名称" prop="kpiName">
          <el-input v-model="form.kpiName" placeholder="请输入指标名称" />
        </el-form-item>
        <el-form-item label="指标描述" prop="kpiDescription">
          <el-input v-model="form.kpiDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="指标分数" prop="kpiGrading">
          <el-input v-model="form.kpiGrading" placeholder="请输入指标分数" />
        </el-form-item>
        <el-form-item label="指标权重" prop="kpiWeight">
          <el-input v-model="form.kpiWeight" placeholder="请输入指标权重" />
        </el-form-item>
        <el-form-item label="父级指标" prop="kpiParentId">
          <el-input v-model="form.kpiParentId" placeholder="请输入父级指标" />
        </el-form-item>
        <el-form-item label="0:定量 1：定性" prop="kpiType">
          <el-select v-model="form.kpiType" placeholder="请选择0:定量 1：定性">
            <el-option label="请选择字典生成" value="" />
          </el-select>
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
      }
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