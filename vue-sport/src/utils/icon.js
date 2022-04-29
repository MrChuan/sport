import Vue from "vue";
import SelectIcon from 'e-icon-picker';
//基本彩色图库
import "e-icon-picker/lib/symbol";
//基本样式，包含基本图标
import 'e-icon-picker/lib/index.css';
//font-awesome 图标库
import 'font-awesome/css/font-awesome.min.css';
//element-ui 图标库
import 'element-ui/lib/theme-chalk/icon.css';

//全局删除增加图标
Vue.use(SelectIcon,{
    FontAwesome: true,
    ElementUI: true,
    eIcon: true,//自带的图标，来自阿里妈妈
    eIconSymbol: true,//是否开启彩色图标
    addIconList: [],
    removeIconList: [],
    zIndex: 3100//选择器弹层的最低层,全局配置
});