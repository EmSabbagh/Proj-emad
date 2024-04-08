<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="Title" prop="title">
        <el-input v-model="queryParams.title" placeholder="请输入Title" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="piblishtime">
        <el-date-picker v-model="daterangePublishTime" style="width: 240px" value-format="yyyy-MM-dd" type="daterange" range-separator="-" start-placeholder="star" end-placeholder="end"></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">seach</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">resive</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['culture:event:add']">add</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['culture:event:edit']">revise</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handldelete" v-hasPermi="['culture:event:remove']">del</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['culture:event:export']">Export</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="eventList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="Title" align="center" prop="title" />
      <el-table-column label="content" align="center" prop="content" />
      <el-table-column label="piblishtime" align="center" prop="publishTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publishTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="publishpeople" align="center" prop="publishUser">
        <template slot-scope="scope">
          {{ getUserById(scope.row.publishUser) }}
        </template>
      </el-table-column>
      <el-table-column label="operate" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['culture:event:edit']">revise
</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handldelete(scope.row)" v-hasPermi="['culture:event:remove']">del</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改特色事件对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="Title" prop="title">
          <el-input v-model="form.title" placeholder="Title" />
        </el-form-item>
        <el-form-item label="content">
          <editor v-model="form.content" :min-height="192" />
        </el-form-item>
        <el-form-item label="piblishtime" prop="publishTime">
          <el-date-picker clearable v-model="form.publishTime" type="date" value-format="yyyy-MM-dd" placeholder="piblishtime">
          </el-date-picker>
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
  listEvent,
  getEvent,
  delEvent,
  addEvent,
  updateEvent,
} from "@/api/culture/event";
import { listUser } from "@/api/system/user";
export default {
  name: "Event",
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
      // 特色事件表格数据
      eventList: [],
      // 弹出层Title
      title: "",
      // 是否显示弹出层
      open: false,
      // 发布人时间范围
      daterangePublishTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        content: null,
        publishTime: null,
        publishUser: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      userOptions: [],
    };
  },
  created() {
    this.getList();
    this.getUserList();
  },
  methods: {
    getUserList() {
      listUser().then((res) => {
        this.userOptions = res.rows;
        console.log(this.userOptions);
      });
    },
    getUserById(id) {
      const user = this.userOptions.find((user) => user.userId === id);
      return user ? user.userName : "未知"; // 如果找不到，返回 '未知'
    },
    /** 查询特色事件列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (
        null != this.daterangePublishTime &&
        "" != this.daterangePublishTime
      ) {
        this.queryParams.params["beginPublishTime"] =
          this.daterangePublishTime[0];
        this.queryParams.params["endPublishTime"] =
          this.daterangePublishTime[1];
      }
      listEvent(this.queryParams).then((response) => {
        this.eventList = response.rows;
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
        title: null,
        content: null,
        publishTime: null,
        publishUser: null,
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
      this.daterangePublishTime = [];
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
      this.title = "addevent";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getEvent(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "resiveevent";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateEvent(this.form).then((response) => {
              this.$modal.msgSuccess("success");
              this.open = false;
              this.getList();
            });
          } else {
            addEvent(this.form).then((response) => {
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
        .$confirm('Are you sure you want to remove the selected featured event?',{
          confirmButtonText: 'confirm',
          cancelButtonText: 'cancel',
        })
        .then(function () {
          return delEvent(ids);
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
        "culture/event/export",
        {
          ...this.queryParams,
        },
        `event_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
