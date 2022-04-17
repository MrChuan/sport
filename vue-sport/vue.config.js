module.exports = {
  lintOnSave:false,
  devServer : {
    //设置端口
    port : 8081,
    //是否启动时打开浏览器
    //open:true,
    //设置代理
    proxy:{
      //代理的跟路径
      '/' :{
        //是否开启websocket
        ws:false,
        //代理后端的路径
        target:'http://localhost:8080',
        //是否开启跟路径转换
        changeOrigin:true,
        //代理路径更改
        pathReWrite:{
          '^/':'/'
        }

      }
    }
  }
}
