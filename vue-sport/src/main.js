import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import '@/utils/ajax';
//引入饿了么UI
import '@/utils/elementui';
//引入全局样式
import '@/assets/global.css';
// 引入自定义指令
import '@/utils/permission';
// 引入图标库组件
import '@/utils/icon';

Vue.config.productionTip = false;
Vue.config.devtools = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
