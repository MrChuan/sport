<template>
  <div>
    用户管理
    <!--做角色判断-->
    <el-button v-hasRole="['ADMIN','PTR']">删除用户（角色）</el-button>

    <el-button v-hasPermi="['USER_DELETE']">删除用户（权限）</el-button>
  </div>
</template>

<script>
import {mapState} from 'vuex';
export default {
  name: "user",
  data(){
    return{

    }
  }

}
</script>

<style scoped>

</style>