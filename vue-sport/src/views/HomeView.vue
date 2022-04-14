<template>
  <div class="home">
    <img @click="click" alt="Vue logo" src="../assets/logo.png" />
  </div>
</template>

<script>
import axios from 'axios';
// @ is an alias to /src
export default {
  name: "HomeView",
  components: {

  },
  methods:{
    click(){
      this.$ajax.get('http://localhost:8080/test').then((res)=>{
        console.log(res);
      }).catch((err)=>{
        console.log(err);
      });
    }
  }
};
</script>
