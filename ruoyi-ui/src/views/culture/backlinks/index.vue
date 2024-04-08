<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="name" prop="name">
        <el-input v-model="queryParams.name" placeholder="please input name" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">seach</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">reset</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['culture:backlinks:add']">add</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['culture:backlinks:edit']">revise</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handldelete" v-hasPermi="['culture:backlinks:remove']">del</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['culture:backlinks:export']">Export</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="backlinksList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="name" align="center" prop="name" />
      <el-table-column label="link" align="center" prop="links" />
      <el-table-column label="operate" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['culture:backlinks:edit']">revise</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handldelete(scope.row)" v-hasPermi="['culture:backlinks:remove']">del</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改外链管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="name" prop="name">
          <el-input v-model="form.name" placeholder="Please enter a name" />
        </el-form-item>
        <el-form-item label="link" prop="links">
          <el-input v-model="form.links" placeholder="Please enter the link" />
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
  listBacklinks,
  getBacklinks,
  delBacklinks,
  addBacklinks,
  updateBacklinks,
} from "@/api/culture/backlinks";

export default {
  name: "Backlinks",
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
      // 外链管理表格数据
      backlinksList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        links: null,
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
    /** 查询外链管理列表 */
    getList() {
      this.loading = true;
      listBacklinks(this.queryParams).then((response) => {
        this.backlinksList = response.rows;
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
        name: null,
        links: null,
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
      this.title = "Add link management";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getBacklinks(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "Modify the management of external links";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateBacklinks(this.form).then((response) => {
              this.$modal.msgSuccess("The modification was successful");
              this.open = false;
              this.getList();
            });
          } else {
            addBacklinks(this.form).then((response) => {
              this.$modal.msgSuccess("The new is successful");
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
        .$confirm('Check whether to confirm that the del external link management number is"' + ids + '"data items？',{
          confirmButtonText: "confirm",
          cancelButtonText: "cancel",
        })
        .then(function () {
          return delBacklinks(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("delsuccess");
        })
        .catch(() => {});
    },
    /** Export按钮操作 */
    handleExport() {
      this.download(
        "culture/backlinks/export",
        {
          ...this.queryParams,
        },
        `backlinks_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
