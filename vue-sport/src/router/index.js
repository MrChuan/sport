import Vue from "vue";
import VueRouter from "vue-router";
import Login from '@/views/login';
import Home from '@/views/Home';
import ajax from '@/utils/ajax';
import store from "@/store";
import {formatMenu} from "@/utils/initMenus";
import user from "@/views/system/user";


Vue.use(VueRouter);

const routes = [
  {
    path:'/login',
    component: Login,
  },
  {
    path: '/',
    component: Home,
  }
];

const router = new VueRouter({
  routes,
});
/**
 * 路由导航
 * to:去那个页面
 * from:从那个页面来
 * next:放行到那个页面
 */
router.beforeEach((to,from,next)=>{
  //console.log("vueRouter:",router)
  //判断用户是否登录
  const token = sessionStorage.getItem('token');
  if (!token){
    //如果路径是去login,则直接放行  ，否则去登录页面
    if (to.path ==='/login'){
      next();
    }else {
      next('/login?redirect=${to.fullPath}');
    }
  }else {
    //向后端发送请求，获取用户基本信息
    ajax.get('/user/getInfo').then((res) =>{
      //console.log('router:用户基本信息',res);
      const user = res.data.data;
      store.commit('setName',user.username);
      store.commit('setAvatar',user.avatar);
      if (user.roles.length > 0){
        //添加角色 菜单 权限
        store.commit('setRoles',user.roles);
        //格式化菜单
        const menuList = formatMenu(user.menus);
        router.addRoutes(menuList);
        //存到内存中
        store.commit("setMenus",menuList);

        store.commit("setPermissions",user.permissions);
      }
    });
    //已经登陆
    if (to.path === '/login'){
        next("/");
    }else {
      next();
    }
  }
});

export default router;
