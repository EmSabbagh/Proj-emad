<template>
  <el-form ref="form" :model="user" :rules="rules" label-width="80px">
    <el-form-item label="Old passwords" prop="oldPassword">
      <el-input v-model="user.oldPassword" placeholder="Please enter your old password" type="password" show-password/>
    </el-form-item>
    <el-form-item label="新密码" prop="newPassword">
      <el-input v-model="user.newPassword" placeholder="Please enter a new password" type="password" show-password/>
    </el-form-item>
    <el-form-item label="确认密码" prop="confirmPassword">
      <el-input v-model="user.confirmPassword" placeholder="Please confirm the new password" type="password" show-password/>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" size="mini" @click="submit">save</el-button>
      <el-button type="danger" size="mini" @click="close">close</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { updateUserPwd } from "@/api/system/user";

export default {
  data() {
    const equalToPassword = (rule, value, callback) => {
      if (this.user.newPassword !== value) {
        callback(new Error("The password entered twice is inconsistent"));
      } else {
        callback();
      }
    };
    return {
      user: {
        oldPassword: undefined,
        newPassword: undefined,
        confirmPassword: undefined
      },
      // 表单校验
      rules: {
        oldPassword: [
          { required: true, message: "The old password can't be empty", trigger: "blur" }
        ],
        newPassword: [
          { required: true, message: "The new password cannot be empty", trigger: "blur" },
          { min: 6, max: 20, message: "The length is between 6 and 20 characters", trigger: "blur" },
          { pattern: /^[^<>"'|\\]+$/, message: "It cannot contain illegal characters：< > \" ' \\\ |", trigger: "blur" }
        ],
        confirmPassword: [
          { required: true, message: "The confirmation password cannot be empty", trigger: "blur" },
          { required: true, validator: equalToPassword, trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    submit() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          updateUserPwd(this.user.oldPassword, this.user.newPassword).then(response => {
            this.$modal.msgSuccess("The modification was successful");
          });
        }
      });
    },
    close() {
      this.$tab.closePage();
    }
  }
};
</script>
