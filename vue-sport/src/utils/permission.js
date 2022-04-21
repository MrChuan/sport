import Vue from "vue";
import store from '@/store';
import permission from "@/views/system/permission";
import {iterator} from "core-js/stable/dom-collections";

/**
 * directive 自定义指令的方法
 * 参数一 指令
 * 参数二 实现方法
 */
Vue.directive('hasRole',{
    inserted(el,binding){
        //获取指令传过来的数据
        const { value } = binding;
        console.log(value);

        //获取角色信息
        const roles =store.state.roles;
        //定义超级管理员
        const admin = 'SUPER_ADMIN';
        //判断指令是否传值，传值是否是一个数组  数组是否大于0
        if (value && value instanceof Array && value.length > 0){
            /**
             * some 适用于检测数组中的元素是否满足指定的条件 并不会改变原数组
             * 返回是 true / false
             */
            const hasRole = roles.some(item => {
                /**
                 * include :用于判断字符串是否保航某个指定的子字符串
                 */
                return item.code === admin || value.includes(item.code);
             });
            //如果没有该角色
            if (!hasRole){
                //把对应的元素删除
                el.parentNode.removeChild(el);
            }
        }else {
            throw new Error(`请设置${value}角色标签`);
        }

    }
});


Vue.directive('hasPermi',{
    inserted(el,binging){
        const { value } = binging;
        const  permission = store.state.permissions;
        const roles = store.state.roles;

        const admin = 'SUPER_ADMIN';

        if (value && value instanceof Array && value.length > 0){
            const hasPermi = permission.some(item => {
                return item.code === admin || value.includes(item.code);
            });

            const hasRole = roles.some(item => {
                return item.code === admin || value.includes(item.code);
            });

            if (!hasPermi && !hasRole){
                el.parentNode.removeChild(el);
            }
        }else {
            throw new Error(`请设置响应权限！`);
        }
    }
});