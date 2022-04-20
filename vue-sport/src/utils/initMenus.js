import Home from "@/views/Home";

/**
 * 格式化菜单，将菜单转换为组件
 * @param menus
 */
export const formatMenu = (menus) => {
    /**迭代菜单，并对菜单进行改造 返回格式化后的菜单
     * 用filter 创建一个新的数组 ， 新数组中的元素通过指定的符合条件的数据进行返回
     * let arr = []
     * menus.forEach(item => { 条件...  arr.push( {}) });
     * return arr;
     */
    const  route = menus.filter(item =>{
        //
        item.component = (item.component === 'home') ? Home : loadView(item.component);

        item.meta ={title : item.title}

        //处理子菜单
        if (item.children && item.children.length > 0){
            formatMenu(item.children);
        }
        return true;
    });
    return route;
}
/**
 * 路由懒加载
 * @param view 组件路径
 * @returns 路由组件
 */
const loadView = (view) => {
    //return (resolve) => require(['@/views/${view}.vue'],resolve);  //会报错: " ' " 换成  " ` "
    return (resolve) => require([`@/views/${view}.vue`],resolve);
}