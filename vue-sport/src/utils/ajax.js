import Vue from 'vue';
import axios from "axios";
import {Message} from "element-ui";

/*
1.先引入包
2.
*/
/*
ajax实例，并设置请求超时时间
如果不设置，前端则会报400
 */
const ajax = axios.create({
    baseURL:'http://localhost:8080',
    timeout:100000
});

/*
响应拦截器：响应后端返回错误异常
 */

ajax.interceptors.response.use((res)=>{
    if (!res.data.flag){
        Message.error(res.data.message);
    }
    Message.success(res.data.message);
    console.log(res);
    return res;
},(err)=>{
    console.log('异常',err.response);
    if (err.response.status === 400){
        Message.error(err.response.data.message);
    }else if(err.response.status === 401){
        Message.error("您未登录，请登录在继续操作！");
    }else if (err.response.status === 403){
        Message.error(err.response.data.message);
    }else if (err.response.status === 404){
        Message.error("后端接口未找到！");
    }else if (err.response.status === 500){
        Message.error("后端异常-->" + err.response.data.message);
    }else {
        Message.error("未知错误！！！");
    }
})
Vue.prototype.$ajax = ajax;