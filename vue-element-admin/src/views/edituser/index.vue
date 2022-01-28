<template>

  <div class="app-container">
    <el-form ref="form" :model="user" label-width="120px">
      <el-form-item label="用户名">
        <el-input v-model="user.userName"/>
      </el-form-item>
      <el-form-item label="用户年龄">
        <el-input v-model="user.userAge"/>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="user.userSex">
          <el-radio label="男" value="男"/>
          <el-radio label="女" value="女"/>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="密保问题">
        <el-input v-model="user.encryptedProblem"/>
      </el-form-item>
      <el-form-item label="用户密码">
        <el-input type="password" v-model="user.password"/>
      </el-form-item>

      <el-form-item label="用户电话">
        <el-input v-model="user.userTel"/>
      </el-form-item>
      <el-form-item label="用户状态">
        <el-select v-model="user.stateId" placeholder="请选择">
          <el-option label="认证中" value="1"/>
          <el-option label="已认证" value="2"/>
          <el-option label="认证失败" value="3"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">编辑</el-button>
        <el-button @click="onCancel()">取消</el-button>
      </el-form-item>
    </el-form>
    <pan-thumb :image="image"/>

    <el-button type="primary" icon="el-icon-upload" style="position: absolute;bottom: 15px;margin-left: 40px;"
               @click="imagecropperShow=true"
    >
      修改头像
    </el-button>

    <image-cropper
      v-show="imagecropperShow"
      :key="imagecropperKey"
      :width="300"
      :height="300"
      :url="uploadUrl"
      lang-type="en"
      @close="close"
      @crop-upload-success="cropSuccess"
    />
  </div>
</template>

<script>
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'

export default {
  components: { ImageCropper, PanThumb },
  data() {
    return {
      imagecropperShow: false,
      imagecropperKey: 0,
      user: {
        'tState': {
          'stateId': null,
          'stateName': '认证中'
        },
        'userId': 1,
        'userName': 'yourname',
        'userAge': 123456,
        'userSex': '男',
        'password': '123456',
        'encryptedProblem': '123',
        'fanNum': null,
        'userTel': '15566666626',
        'registerDate': '2021-04-17 15:17:10',
        'iconUrl': 'http://localhost:8081/user/getIcon/icon1.jpg',
        'stateId': 1
      },
      // eslint-disable-next-line no-undef
      image: '',
      uploadUrl: 'http://localhost:8081/admin/upload?userId='
    }
  },
  created() {
    this.fetchDataById()
  },
  methods: {
    fetchDataById() {
      var id = this.$route.params.id
      this.uploadUrl = this.uploadUrl + id
      var vm = this
      this.axios({
        method: 'GET',
        url: 'http://localhost:8081/admin/getUserById?id=' + id
      }).then(function(resp) {
        vm.user = resp.data
        vm.image = 'http://localhost:8081' + vm.user.iconUrl
      })
    },

    cropSuccess(resData) {
      this.imagecropperShow = false
      this.imagecropperKey = this.imagecropperKey + 1
      this.image = resData
      // eslint-disable-next-line no-implied-eval

      this.$message({
        message: '上传成功',
        type: 'success',
        duration: 2000
      })
      setTimeout(function() {
        location.reload()
      }, 1000)
    },
    close() {
      this.imagecropperShow = false
    },
    onSubmit() {
      var vm = this
      this.axios({
        method: 'POST',
        data: vm.user,
        url: 'http://localhost:8081/admin/editUser'
      }).then(function(resp) {
        vm.$message({
          message: '修改成功',
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

.avatar {
  width: 200px;
  height: 200px;
  border-radius: 50%;
}
</style>

