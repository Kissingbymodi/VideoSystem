<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="分类名称">
        <el-input v-model="videoTypeName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="warning" @click="onSubmit">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="success" @click="addCategory">添加分类</el-button>
      </el-form-item>
    </el-form>
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
      <el-table-column align="center" label="类型">
        <template slot-scope="scope">
          {{ scope.row.typeName }}
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="editCategory(scope.row.videotypeId)">
            编辑
          </el-button>
          <el-button type="danger" size="mini" @click="delCategory(scope.row.videotypeId)">
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
      videoTypeName: '',
      total: 0,
      listQuery: {
        page: 1,
        limit: 5
      },
      list: [
        {
          'videotypeId': 1,
          'typeName': '影视'
        }
      ]
    }
  },
  created() {
    // this.fetchData()
    this.getList()
  },

  methods: {
    onSubmit() {
      var vm = this
      this.axios({
        method: 'GET',
        url: 'http://localhost:8081/admin/searchVideoType?pageNum=' + vm.listQuery.page + '&pageSize=' + vm.listQuery.limit + '&typeName=' + vm.videoTypeName
      }).then(function(resp) {
        vm.total = resp.data.total // 讲pageInfo中的total放到vm的total
        vm.list = resp.data.list
      })
    },
    getList() {
      var vm = this
      this.axios({
        method: 'GET',
        url: 'http://localhost:8081/admin/categoryPageInfo?pageNum=' + vm.listQuery.page + '&pageSize=' + vm.listQuery.limit
      }).then(function(resp) {
        vm.total = resp.data.total // 讲pageInfo中的total放到vm的total
        vm.list = resp.data.list
      })
    },
    addCategory() {
      this.$router.push('/addcategory/index')
    },
    editCategory(id) {
      this.$router.push('/editcategory/index/' + id)
    },
    delCategory(id) {
      var vm = this
      this.$confirm('此操作将删除分类, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        vm.axios({
          method: 'GET',
          url: 'http://localhost:8081/admin/deleteCategory/' + id
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
          message: '已取消删除'
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
