<template>
  <div class="app-container">
    <div>
      <el-button type="success" @click="addvideo">添加视频</el-button>
    </div>
    <el-table
      :data="list"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="序号" width="95">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="视频名称">
        <template slot-scope="scope">
          {{ scope.row.videoTitle }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="播放量" width="95">
        <template slot-scope="scope">
          {{ scope.row.viewNum }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="视频类型" width="95">
        <template slot-scope="scope">
          {{ scope.row.videoType.typeName }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="编辑日期" >
        <template slot-scope="scope">
          {{ scope.row.editDate }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户" >
        <template slot-scope="scope">
          {{ scope.row.tUser.userName }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="状态">
        <template slot-scope="scope">
          {{ scope.row.videoState.stateName }}
        </template>
      </el-table-column>
<!--      <el-table-column label="注册日期" width="160">
        <template slot-scope="scope">
          {{ scope.row.registerDate }}
        </template>
      </el-table-column>
      <el-table-column label="用户状态">
        <template slot-scope="scope">
          {{ scope.row.tState.stateName }}
        </template>
      </el-table-column>-->

      <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="restorevideo(scope.row.videoId)">
            恢复
          </el-button>
          <el-button type="danger" size="mini" @click="delvideo(scope.row.videoId)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit"
                @pagination="getList"
    />

  </div>
</template>
<script>
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
export default {
  components: { Pagination },
  data() {
    return {
      total: 0,
      listQuery: {
        page: 1,
        limit: 5
      },
      list: [
        {
          'videoId': 1,
          'videoTitle': '霸王别姬',
          'videoInfo': '段小楼（张丰毅）与程蝶衣（张国荣）是一对打',
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
          'thumbnailUrl': null,
          'viewNum': 107,
          'ppNum': 7
        }
      ]
    }
  },
  created() {
    // this.fetchData()
    this.getList()
  },

  methods: {
    getList() {
      var vm = this
      this.axios({
        method: 'GET',
        url: 'http://localhost:8081/admin/underVideoPageInfo?pageNum=' + vm.listQuery.page + '&pageSize=' + vm.listQuery.limit
      }).then(function(resp) {
        vm.total = resp.data.total // 讲pageInfo中的total放到vm的total
        vm.list = resp.data.list
      })
    },
    addvideo() {
      this.$router.push('/addvideo/index')
    },
    restorevideo(id) {
      var vm = this
      this.$confirm('此操作将上架该视频, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        vm.axios({
          method: 'GET',
          url: 'http://localhost:8081/admin/restoreVideo/' + id
        }).then(function(resp) {
          if (resp.data === 'success') {
            vm.$message({
              message: '恢复成功',
              type: 'success'
            })
            vm.getList()
          }
          // eslint-disable-next-line handle-callback-err
        }).catch(function(error) {
          vm.$message.error('恢复失败')
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消下架'
        });
      });
    },
    delvideo(id) {
      var vm = this
      this.$confirm('此操作将永久删除该视频, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.axios({
          method: 'GET',
          url: 'http://localhost:8081/admin/deleteVideo/' + id
        }).then(function(resp) {
          if (resp.data === 'success') {
            vm.$message({
              message: '删除成功',
              type: 'success'
            })
            vm.getList()
          }
          // eslint-disable-next-line handle-callback-err
        }).catch(function(error) {
          vm.$message.error('删除失败')
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消下架'
        })
      })
    },
    // 获取用户列表
    fetchData() {
      var vm = this
      this.axios({
        method: 'GET',
        url: 'http://localhost:8081/admin/list'
      }).then(function(resp) {
        vm.list = resp.data
        console.log(resp.data)
      })
    }
  }
}
</script>
