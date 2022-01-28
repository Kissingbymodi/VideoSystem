<template>
  <div class="app-container">
    <el-form ref="form" :model="list" label-width="120px">
      <el-form-item label="分类名称">
        <el-input v-model="list.typeName"/>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">添加</el-button>
        <el-button @click="onCancel()">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      list: {
        'videotypeId': 1,
        'typeName': '影视'
      }
    }
  },
  methods: {

    onSubmit() {
      var vm = this
      this.axios({
        method: 'POST',
        data: vm.user,
        url: 'http://localhost:8081/admin/addUser'

      }).then(function(resp) {
        vm.$message({
          message: '添加成功',
          type: 'success'
        })
        vm.$router.push('/user')
      })
    },
    onCancel() {
      this.$message({
        message: '返回中!',
        type: 'success'
      })
      this.$router.push('/user')
    }
  }
}
</script>

<style scoped>
.line {
  text-align: center;
}

.el-input {
  width: 200px;
}
</style>

