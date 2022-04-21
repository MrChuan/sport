import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import '@/utils/ajax';
//引入饿了么UI
import '@/utils/elementui';
//引入全局样式
import '@/assets/global.css';
import '@/utils/permission';
import '@/assets/iconfont/iconfont.css';

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
