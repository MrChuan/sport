<template>
  <div >
    <el-image style="width: 100px; height: 100px;" :src="require('../assets/logo.png')" fit="fit"/>
    <div class="from-class">
      <el-card>
        <el-form :model="from" :rules="rules" ref="from" label-width="50px" >
          <el-form-item label="账号" prop="username">
            <el-input  v-model="from.username" />
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="from.password" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submit">提交</el-button>
            <el-button @click="reset">重置</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>

  </div>
</template>

<script>
export default {
  name: "login",
  data(){
    return{
      //表单对象
      from:{

      },
      //表单提交规则
      rules:{
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 30 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],

      }
    }

  },
  //页面加载函数
  created() {
  },
  //存放所有的方法
  methods:{
    //登陆的方法
    submit(){
      this.$refs.from.validate((valid)=>{
        if(valid){
          //如果成功
          alert("11111");
        }else {
          console.log("error =数据校验失败");
          return this.$message.error("数据校验失败");
        }
      });
    },
    //重置
    reset(){
      this.$refs.from.resetFields();
    }

  }
}
</script>

<style scoped>
  .from-class{
    width: 30%;
    margin: 200px 475px  auto;

  }
</style>