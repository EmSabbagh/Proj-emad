<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="Name" prop="name">
        <el-input v-model="queryParams.name" placeholder="Please input name" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="StarTime">
        <el-date-picker v-model="daterangeActivityTime" style="width: 240px" value-format="yyyy-MM-dd" type="daterange" range-separator="-" start-placeholder="star" end-placeholder="end"></el-date-picker>
      </el-form-item>
      <el-form-item label="PublishTime">
        <el-date-picker v-model="daterangePublishTime" style="width: 240px" value-format="yyyy-MM-dd" type="daterange" range-separator="-" start-placeholder="star" end-placeholder="end"></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">seach</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">resize</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['culture:activity:add']">add</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['culture:activity:edit']">revise</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handldelete" v-hasPermi="['culture:activity:remove']">del</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['culture:activity:export']">Export</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="activityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="name" align="center" prop="name" />
      <el-table-column label="address" align="center" prop="place" />
      <el-table-column label="time" align="center" prop="activityTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.activityTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="publishPeople" align="center" prop="publishUser">
        <template slot-scope="scope">
          {{ getUserById(scope.row.publishUser) }}
        </template>
      </el-table-column>
      <el-table-column label="publishtime" align="center" prop="publishTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publishTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="operate" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['culture:activity:edit']">revise</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handldelete(scope.row)" v-hasPermi="['culture:activity:remove']">del</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或revise活动对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="name" prop="name">
          <el-input v-model="form.name" placeholder="please in put name" />
        </el-form-item>
        <el-form-item label="content">
          <editor v-model="form.content" :min-height="192" />
        </el-form-item>
        <el-form-item label="address" prop="place">
          <el-input v-model="form.place" placeholder="please in put address" />
        </el-form-item>
        <el-form-item label="time" prop="activityTime">
          <el-date-picker clearable v-model="form.activityTime" type="date" value-format="yyyy-MM-dd" placeholder="please input time">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="publishtime" prop="publishTime">
          <el-date-picker clearable v-model="form.publishTime" type="date" value-format="yyyy-MM-dd" placeholder="please input time">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">ok</el-button>
        <el-button @click="cancel">Cancel</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listActivity,
  getActivity,
  delActivity,
  addActivity,
  updateActivity,
} from "@/api/culture/activity";
import { listUser } from "@/api/system/user";
export default {
  name: "Activity",
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
      // 活动表格数据
      activityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 发布时间时间范围
      daterangeActivityTime: [],
      // 发布时间时间范围
      daterangePublishTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        content: null,
        place: null,
        activityTime: null,
        publishUser: null,
        publishTime: null,
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
      return user ? user.userName : "Unknown"; // 如果找不到，返回 '未知'
    },
    /** 查询活动列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (
        null != this.daterangeActivityTime &&
        "" != this.daterangeActivityTime
      ) {
        this.queryParams.params["beginActivityTime"] =
          this.daterangeActivityTime[0];
        this.queryParams.params["endActivityTime"] =
          this.daterangeActivityTime[1];
      }
      if (
        null != this.daterangePublishTime &&
        "" != this.daterangePublishTime
      ) {
        this.queryParams.params["beginPublishTime"] =
          this.daterangePublishTime[0];
        this.queryParams.params["endPublishTime"] =
          this.daterangePublishTime[1];
      }
      listActivity(this.queryParams).then((response) => {
        this.activityList = response.rows;
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
        content: null,
        place: null,
        activityTime: null,
        publishUser: null,
        publishTime: null,
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
      this.daterangeActivityTime = [];
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
      this.title = "add activity";
    },
    /** revise按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getActivity(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "revise activity";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateActivity(this.form).then((response) => {
              this.$modal.msgSuccess("revise success");
              this.open = false;
              this.getList();
            });
          } else {
            addActivity(this.form).then((response) => {
              this.$modal.msgSuccess("add success");
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
        .$confirm('Do you confirm that the del activity number is"' + ids + '"data items？',{
          confirmButtonText: "confirm",
          cancelButtonText: "cancel",
        })
        .then(function () {
          return delActivity(ids);
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
        "culture/activity/export",
        {
          ...this.queryParams,
        },
        `activity_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
