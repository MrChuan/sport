import Vue from 'vue';
import axios from "axios";

/*
1.先引入包
2.
*/
const ajax = axios.create({
    baseURL:'http://localhost:8080'
});

Vue.prototype.$ajax = ajax;