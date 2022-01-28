<template>

  <div class="app-container">
    <el-form ref="form" :model="video" label-width="120px">
      <el-form-item label="视频名称">
        <el-input v-model="video.videoTitle"/>
      </el-form-item>
      <el-form-item label="视频信息">
        <el-col :span="8">
          <el-input :rows="5" v-model="video.videoInfo" type="textarea"/>
        </el-col>
      </el-form-item>
      <el-form-item label="视频类型">
        <el-select v-model="video.videoTypeId" placeholder="请选择">
          <el-option v-for="item in videoTypeList" :key="item.typeName" :label="item.typeName"
                     :value="item.videotypeId"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="视频状态">
        <el-select v-model="video.videoStateId" placeholder="请选择">
          <el-option v-for="item in stateList" :key="item.stateName" :label="item.stateName" :value="item.stateId"/>
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
      修改缩略图
    </el-button>

    <image-cropper
      v-show="imagecropperShow"
      :key="imagecropperKey"
      :width="210"
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
      video: {
        'videoId': 1,
        'videoTitle': '霸王别姬',
        'videoInfo': '段小楼（张丰毅）与程蝶衣（张国荣）是一对打',
        'videoTypeId': 1,
        'videoStateId': 4,
        'thunmbnailUrl': '/img/defaultvideoicon.jpg',
        'videoType': {
          'videotypeId': 1,
          'typeName': '影视'
        },
        'editDate': '2021-04-26T11:59:12.000+0000',
        'tUser': {
          'userId': 1,
          'userName': 'yourname',
          'userAge': 22,
          'userSex': '男',
          'password': '123456',
          'encryptedProblem': '123',
          'fanNum': 0,
          'userTel': '15566666626',
          'registerDate': '2021-04-17 15:17:10',
          'iconUrl': '/user/getIcon/icon1.png',
          'stateId': 1
        },
        'videoState': {
          'stateId': 4,
          'stateName': '已上架'
        },
        'videoUrl': '/static/video/bwbj.mp4',
        'thumbnailUrl': '/video/getVideoImage/video1.png',
        'viewNum': 107,
        'ppNum': 7
      },
      // eslint-disable-next-line no-undef
      stateList: [{
        'stateId': '4',
        'stateName': '已上架'
      }],
      videoTypeList: [{
        'typeName': '影视',
        'videotypeId': 1
      }
      ],
      image: '',
      uploadUrl: 'http://localhost:8081/admin/editThumbnailImageUpload?videoId='
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
        url: 'http://localhost:8081/admin/getVideoById?id=' + id
      }).then(function(resp) {
        vm.video = resp.data
        vm.image = 'http://localhost:8081' + vm.video.thumbnailUrl
      })
      this.axios({
        method: 'GET',
        url: 'http://localhost:8081/admin/getStateList'
      }).then(function(resp) {
        vm.stateList = resp.data
      })
      this.axios({
        method: 'GET',
        url: 'http://localhost:8081/admin/getVideoTypeList'
      }).then(function(resp) {
        vm.videoTypeList = resp.data
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
      this.$router.push('/video')
    },
    close() {
      this.imagecropperShow = false
    },
    onSubmit() {
      var vm = this
      this.axios({
        method: 'POST',
        data: vm.video,
        url: 'http://localhost:8081/admin/editVideo'
      }).then(function(resp) {
        vm.$message({
          message: '修改成功',
          type: 'success'
        })
        vm.$router.push('/video')
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
  width: 120px;
  height: 200px;
}
</style>

