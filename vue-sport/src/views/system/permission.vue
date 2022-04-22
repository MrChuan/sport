<template>
  <div>
    <el-card shadow="hover">
      <el-row>
        <el-col :span="8">
          <el-input  placeholder="请输入内容" v-model="queryInfo.queryString" clearable class="input-with-select">
            <el-button slot="append" icon="el-icon-search" @click="findPage"></el-button>
          </el-input>
        </el-col>
        <el-col :span="2">
          <el-button @click="insert" type="primary" style="margin-left: 10px">添加权限信息</el-button>
        </el-col>
      </el-row>

      <el-table :data="tableData"  stripe>
        <el-table-column prop="id"  label="编号" width="180" align="center">
        </el-table-column>
        <el-table-column prop="label" label="权限名称" width="180" align="center">
        </el-table-column>
        <el-table-column prop="code" label="权限值"  align="center">
        </el-table-column>
        <el-table-column  label="操作"  align="center">
          <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </el-table-column>
      </el-table>
      <!---
      @size-change:点击页码
      @current-change：输入框输入数码

      --->
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage4"
          :page-sizes="[5,10, 25, 50]"
          :page-size="queryInfo.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total" style="margin-top: 20px">
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "user",
  data() {
    return {
      queryInfo:{
        pageNumber:1,
        pageSize:5,
        queryString:null
      },
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄',
      }, ],
      currentPage1: 5,
      currentPage2: 5,
      currentPage3: 5,
      currentPage4: 4,
      //总记录数
      total:0,
    }
  },
  //页面初始化调用
  created() {
    this.findPage();
  },
  methods: {
    insert(){
      console.log('添加操作')
    },
    /**
     * 分页查询
     */
    findPage(){
      this.$ajax.post('/permission/findPage',this.queryInfo).then((res) =>{
        console.log(res);
        this.tableData = res.data.rows;
        this.total = res.data.total;
      });
    },
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
    /**
     * 页码改变事件
     * @param val
     */
    handleSizeChange(val) {
      //console.log(`每页 ${val} 条`);
      this.queryInfo.pageSize = val;
      this.findPage();
    },
    /**
     * 每页条数改变事件
     * @param val
     */
    handleCurrentChange(val) {
      //console.log(`当前页: ${val}`);
      this.queryInfo.pageNumber = val;
      this.findPage();
    }
  }
}
</script>

<style scoped>

</style>