<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="label" prop="label">
        <el-input v-model="queryParams.label"  clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">seach</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">reset
</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['culture:picture:add']">add</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['culture:picture:edit']">resive</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handldelete" v-hasPermi="['culture:picture:remove']">del</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['culture:picture:export']">Export</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pictureList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="picter" align="center" prop="addr" width="150">
        <template slot-scope="scope">
          <image-preview :src="scope.row.addr" :width="100" :height="100" />
        </template>
      </el-table-column>
      <el-table-column label="label" align="center" prop="label" />
      <el-table-column label="opera" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['culture:picture:edit']">revise</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handldelete(scope.row)" v-hasPermi="['culture:picture:remove']">del</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改画廊列表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="piecter" prop="addr">
          <image-upload v-model="form.addr" limit="1"/>
        </el-form-item>
        <el-form-item label="label" prop="label">
          <el-input v-model="form.label" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">ok</el-button>
        <el-button @click="cancel">cancel</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listPicture,
  getPicture,
  delPicture,
  addPicture,
  updatePicture,
} from "@/api/culture/picture";

export default {
  name: "Picture",
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
      // 画廊列表表格数据
      pictureList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        addr: null,
        label: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询画廊列表列表 */
    getList() {
      this.loading = true;
      listPicture(this.queryParams).then((response) => {
        this.pictureList = response.rows;
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
        id: null,
        addr: null,
        label: null,
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
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "add picture";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getPicture(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "revise picture";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updatePicture(this.form).then((response) => {
              this.$modal.msgSuccess("success");
              this.open = false;
              this.getList();
            });
          } else {
            addPicture(this.form).then((response) => {
              this.$modal.msgSuccess("success");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** del按钮操作 */
    handldelete(row) {
      const ids = row.id || this.ids;
      this
        .$confirm('confirm del?')
        .then(function () {
          return delPicture(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("success");
        })
        .catch(() => {});
    },
    /** Export按钮操作 */
    handleExport() {
      this.download(
        "culture/picture/export",
        {
          ...this.queryParams,
        },
        `picture_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
