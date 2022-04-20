<template>
  <el-container class="main-class">
    <el-header>
      <el-row style="height: 100%">
        <el-col :span="1" style="height: 100%">
          <el-avatar :size="60" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
        </el-col>
        <el-col :span="15">
          个人运动管理平台<span>--管理员({{name}})</span>
        </el-col>
        <el-col class="logout" :span="7">
          <el-button type="info" @click="logout">退出登录</el-button>
        </el-col>
      </el-row>
    </el-header>
    <el-container>
      <el-aside :width="isCollapse ? '60px' : '200px'">
        <div style="font-size: 20px; background-color: #3077c9; color: #fff; cursor: pointer;" @click="isCollapse = !isCollapse">
          <i class="el-icon-s-fold" ></i>
        </div>
        <!--菜单列表 unique-opened只展开一个子菜单
                   router是让菜单为路由模式， 菜单中index属性为path
        -->
            <el-menu
                default-active="2"
                class="el-menu-vertical-demo"
                @open="handleOpen"
                @close="handleClose"
                unique-opened
                router
                :collapse="isCollapse"
                >
              <el-submenu :index="index+ ''" v-for="(parentMenu,index) in menus" :key="index">
                <template slot="title">
                  <i :class="parentMenu.icon"/>
                  <span>{{parentMenu.title}}</span>
                </template>

                  <el-menu-item :index="childrenMenu.path" v-for="(childrenMenu,i) in parentMenu.children" :key="i" >
                    <template slot="title">
                      <i :class="childrenMenu.icon"/>
                      <span>{{childrenMenu.title}}</span>
                    </template>
                  </el-menu-item>

              </el-submenu>
            </el-menu>
      </el-aside>
      <el-main>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item v-for="(item,index) in $router.currentRoute.matched" :key="index">{{item.meta.title}}</el-breadcrumb-item>

        </el-breadcrumb>
        <span v-show="$router.currentRoute.path === '/'" class="main-title">欢迎来到个人运动管理平台！</span>
        <!----作为主体的子路由跳转---->
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
//需要用到缓存
import {mapState} from 'vuex';
export default {
  //计算属性
  computed:{
    ...mapState(['name',"avatar","menus"])
  },
  created() {
    //console.log('菜单',this.menus)
    //console.log("created-->name: " + this.name);
  },
  data () {
    return {
      isCollapse: false
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      //console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      //console.log(key, keyPath);
    },
    logout(){
       this.$confirm('确定退出, 是否继续?', '提示', {
        confirmButtonText: '退出',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
           this.$ajax.get('/user/login').then((res) =>{
           console.log("登录退出:" + res.data.message);
           //清空本地缓存
           sessionStorage.clear();
           //跳转到登录页面
           this.$router.replace('/login');
           this.$message.success(res.data.message);
         });
      }).catch(() => {
        this.$message({
          //取消当前操作
          type: 'info',
          message: '已取消操作'
        });
      });
    }
  }
}

</script>

<style scoped>
/**
scoped受保护的样式，当前style标签下的样式只在当前组件生效，其他组件无法使用，避免样式污染
 */
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;

}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.main-class{
 height: 100%;
}

.title{
  text-align: left;
  font-size: 25px;
  font-family: 华文行楷;
}
.logout{
  text-align: right;
}
.main-title{
  font-size: 50px;
  font-family: 华文楷体;
  color: sandybrown;
}


</style>
