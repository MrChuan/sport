<template>
  <div>
    <el-card shadow="hover">
      <el-row>
        <el-col :span="8">
          <el-input  placeholder="请输入内容" v-model="queryInfo.queryString" clearable @clear="findPage" class="input-with-select">
            <el-button slot="append" icon="el-icon-search" @click="findPage"></el-button>
          </el-input>
        </el-col>
        <el-col :span="2">
          <el-button @click="insert" type="primary" style="margin-left: 10px">添加权限信息</el-button>
        </el-col>
        <el-dialog :title="title" :visible.sync="dialogFormVisible" @close="dialogClose">
          <el-form :model="form" :rules="rules" ref="form">
            <el-form-item label="权限名" :label-width="formLabelWidth" prop="label">
              <el-input v-model="form.label" />
            </el-form-item>
            <el-form-item label="权限值" :label-width="formLabelWidth" prop="code">
              <el-input v-model="form.code"/>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="clickCancel">取 消</el-button>
            <el-button type="primary" @click="clickOk">确 定</el-button>
          </div>
        </el-dialog>
      </el-row>

      <el-table :data="tableData"  stripe>
        <el-table-column type="index"  label="序号" width="180" align="center">
        </el-table-column>
        <el-table-column prop="label" label="权限名称" width="180" align="center">
        </el-table-column>
        <el-table-column prop="code" label="权限值"  align="center">
        </el-table-column>
        <el-table-column  label="操作"  align="center">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!---
      @size-change:点击页码
      @current-change：输入框输入数码

      --->
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="1"
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
      tableData: [],
      //总记录数
      total:0,
      dialogTableVisible: false,
      dialogFormVisible: false,
      title:null,
      form:{
        delivery: false,
      },
      rules: {
        //表单校验
        label: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
        ],
        code: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
        ],
      },
      formLabelWidth: '120px'
    }
  },
  //页面初始化调用
  created() {
    this.findPage();

  },
  methods: {
    insert(){
      console.log('添加操作',this.form);
      this.title = "添加权限";
      this.dialogFormVisible = true;

    },
    /**
     * 关闭表单弹出框事件
     */
    dialogClose(){
      //将整个表单进行重置，并一处校验效果
      this.$refs.form.resetFields();
    },
    /**
     * 点击取消事件
     */
    clickCancel(){
      this.form = {} ;
      this.dialogFormVisible = false;
      //添加一下代码，解决编辑取消更改本地数据问题。
      //重新获取数据填充vue 双向绑定问题
      this.findPage();
    },
     /**
     * 点击确认事件
     */
     clickOk(){
      //进行表单校验
       this.$refs.form.validate((valid) =>{
         //校验不通过
         if (!valid) return this.$message.error('表单校验不通过，请检查！！');
         //通过是否有id 来判断 是新增还是修改：有id 是修改  无id 则是新增
         if (this.form.id === undefined || this.form.id === null){
            this.$ajax.post('/permission/insert',this.form).then((res) =>{
              this.$message.success(res.data.message);
              this.dialogFormVisible = false;
              this.findPage();
            })
         }else {
           this.$ajax.put('/permission/update',this.form).then((res)=>{
             this.$message.success(res.data.message);
             this.dialogFormVisible = false;
             this.findPage();
           })
         }
       })
     },
    /**
     * 分页查询
     */
    findPage(){
      this.$ajax.post('/permission/findPage',this.queryInfo).then((res) =>{
        //console.log(res);
        this.tableData = res.data.rows;
        this.total = res.data.total;
      });
    },
    handleEdit(row) {
      console.log(row);
      this.title = "修改权限";
      this.form = row;
      this.dialogFormVisible = true;
    },
    handleDelete(id) {
      console.log(id);
      this.$confirm(`编号${id}将永久删除，是否继续`,'提示',{
        confirmButtonText:'确定',
        cancelButtonText:'取消',
        type:'warning'
      }).then(() =>{
        //调用后端的删除接口
        this.$ajax.delete(`permission/delete/${id}`).then((res)=>{
          this.$message.success(res.data.message);
          //解决如果一页只有一条数据时查询报错问题

          this.findPage();

          this.queryInfo.pageNumber = 1;

        })
      });
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
      console.log(`当前页: ${val}`);
      this.queryInfo.pageNumber = val;
      this.findPage();
    }
  }
}
</script>

<style scoped>

</style>