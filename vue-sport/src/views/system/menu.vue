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
          <el-button @click="insert" type="primary" v-hasPermi="['MENU_INSERT']" style="margin-left: 10px">添加菜单</el-button>
        </el-col>

        <!--弹出框-->
        <el-dialog :title="title" :visible.sync="dialogFormVisible" @close="dialogClose">
          <el-form :model="form" :rules="rules" ref="form">
            <el-form-item label="是否是父级菜单" :label-width="formLabelWidth" v-if="isChildrenMenu">
              <el-checkbox v-model="isChildrenMenu" :disabled="isVisible" />
            </el-form-item>
            <el-form-item label="父级菜单" prop="parentId" :label-width="formLabelWidth"  v-if="isChildrenMenu">
              <el-select v-model="form.parentId" placeholder="请选择" style="width: 100%" >
                <el-option
                    v-for="(item, index) in parentList"
                    :key="index"
                    :label="item.title"
                    :value="item.id"
                    :disabled="!item.status">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="标题" :label-width="formLabelWidth" prop="title">
              <el-input v-model="form.title"/>
            </el-form-item>
            <el-form-item label="路径" :label-width="formLabelWidth" prop="path">
              <el-input v-model="form.path"/>
            </el-form-item>
            <el-form-item label="图标" :label-width="formLabelWidth" prop="icon">
              <e-icon-picker v-model="form.icon"/>
            </el-form-item>
            <el-form-item label="组件" :label-width="formLabelWidth" prop="component">
              <el-input v-model="form.component"/>
            </el-form-item>
            <el-form-item label="是否启用" :label-width="formLabelWidth" prop="status">
              <el-radio-group v-model="form.status">
                <el-radio :label="true">是</el-radio>
                <el-radio :label="false">否</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="clickCancel">取 消</el-button>
            <el-button type="primary" @click="clickOk">确 定</el-button>
          </div>
        </el-dialog>
      </el-row>

      <el-table :data="tableData"  stripe row-key="id" :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
        <el-table-column prop="title"  label="菜单名称" width="120" align="center" />
        <el-table-column prop="path"  label="前端路由" width="180" align="center" />
        <el-table-column label="菜单图标" width="160" align="left" >
          <template slot-scope="scope" >
            <i :class="scope.row.icon" style="margin-right: 8px" />
            <span>{{scope.row.icon}}</span>
          </template>
        </el-table-column>

        <el-table-column prop="component"  label="菜单组件" width="180" align="center" />
        <el-table-column label="是否启用"  align="center">
          <template slot-scope="scope" >
            <el-switch @change="updateStatus(scope.row)" :disabled="swichStatus(scope.row)" v-model="scope.row.status"/>
          </template>
        </el-table-column>

        <el-table-column  label="操作"  align="center">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--分页-->
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
  data(){
    return{
      queryInfo:{
        pageNumber:1,
        pageSize:5,
        queryString:null
      },
      tableData: [],
      //查询父级菜单列表
      parentList: [],
      isChildrenMenu:true,
      isVisible:false,
      //总记录数
      total:0,
      title:null,
      form:{
        delivery: false,
      },
      dialogTableVisible: false,
      dialogFormVisible: false,
      rules: {
        parentId:[
          { required: true, message:"请选择父级菜单", trigger: 'change' },
        ],
        //表单校验  required 必填
        path: [
          { required: true, message: '请输入菜单路径', trigger: 'blur' },
          { min: 1, max: 100, message: '长度在 3 到 100 个字符', trigger: 'blur' }
        ],
        icon: [
          { required: true, message: '请选择图片', trigger: 'change' },
        ],
        title: [
          { required: true, message: '请输入菜单名称', trigger: 'blur' },
          { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
        ],
        component: [
          { required: true, message: '请输入菜单名称', trigger: 'blur' },
          { min: 1, max:50, message: '长度在 3 到 100 个字符', trigger: 'change' }
        ],
      },
      formLabelWidth: '120px'
    }
  },
  created() {
    this.findPage();
    this.findParent();
  },
  methods :{

    getData1(e){
      console.log(e)
    },

    /**
     * 查询父级菜单
     */
    findParent(){
      this.$ajax.get('/menu/findParent').then((res)=>{
        //console.log("findParent---》",res.data.data)
        this.parentList = res.data.data;
      });
    },
    /**
     * 分页查询
     */
    findPage(){
      this.$ajax.post('/menu/findPage',this.queryInfo).then((res) =>{

        this.tableData = res.data.rows;
        this.total = res.data.total;
        console.log("findpage:",this.tableData);
      });
    },
    /**
     * 添加菜单
     */
    insert(){
      console.log('添加操作',this.form);
      this.title = "添加菜单";
      this.dialogFormVisible = true;
      this.isChildrenMenu =true;
      this.isVisible =false;
    },
    /**
     * 修改菜单
     */
    handleEdit(row) {
      console.log("修改菜单---》",row);
      this.title = "修改菜单";
      //判断是否是一级菜单
      if (row.parentId == 0 ){
        this.isChildrenMenu =false;
      }else {
        //子菜单回显父级菜单
        this.isChildrenMenu =true;
        this.isVisible =true;
      }
      this.form = row;
      this.dialogFormVisible = true;
    },
    /**
     * 删除事件
     */
    handleDelete(id) {
      //console.log(id);
      this.$confirm(`编号${id}将永久删除，是否继续`,'提示',{
        confirmButtonText:'确定',
        cancelButtonText:'取消',
        type:'warning'
      }).then(() =>{
        //调用后端的删除接口
        this.$ajax.delete(`/menu/delete/${id}`).then((res)=>{
          this.$message.success(res.data.message);
          //解决如果一页只有一条数据时查询报错问题
          this.findPage();
          this.queryInfo.pageNumber = 1;

        })
      });
    },
    /**
     * 更新菜单状态
     */
    updateStatus(row){
      //console.log(row);
      this.$ajax.put('/menu/update',row).then((res)=>{
        this.$message.success("状态更新成功！");
        //this.findPage();
      })
    },
    swichStatus(row){
      if (row.children != null){
        return true;
      }else {
        return false;
      }
    },
    /**
     * 关闭表单弹出框事件
     */
    dialogClose(){
      //将整个表单进行重置，并一处校验效果
      this.isChildrenMenu = true;
      this.isVisible =false;
      this.$refs.form.resetFields();

    },
    /**
     * 点击取消事件
     */
    clickCancel(){
      this.form = {};
      this.dialogFormVisible = false;
      this.isChildrenMenu = true;
      this.isVisible =false;
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
          this.$ajax.post('/menu/insert',this.form).then((res) =>{
            this.$message.success(res.data.message);
            this.dialogFormVisible = false;
            this.findPage();
          })
        }else {
          this.$ajax.put('/menu/update',this.form).then((res)=>{
            this.$message.success(res.data.message);
            this.dialogFormVisible = false;
            this.findPage();
          })
        }
      })
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
    },



  }

}
</script>

<style scoped>

</style>