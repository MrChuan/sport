import Vue from "vue";
import VueRouter from "vue-router";
import Login from '@/views/login';
import Home from '@/views/Home';
import ajax from '@/utils/ajax';
import store from "@/store";

Vue.use(VueRouter);

const routes = [
  {
    path:'/login',
    component: Login,
  },
  {
    path: '/home',
    component: Home,
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
    //判断vuex中是否存在用户基本信息
    if (!store.state.roles || store.state.roles.length < 1){
      //向后端发送请求，获取用户基本信息
      ajax.get('/user/getInfo').then((res) =>{
        console.log('router:用户基本信息',res);
        const user = res.data.data;
        store.commit('setName',user.username);
        store.commit('setAvatar',user.avatar);
        if (user.roles.length > 0){
          //添加角色 菜单 权限
          store.commit('setRoles',user.roles);
          store.commit("setMenus",user.menus);
          store.commit("setPermissions",user.permissions);
        }
        return res;
      });
    }

    //已经登陆
    if (to.path === '/login'){
        next("/");
    }else {
      next();
    }
  }
});

export default router;
