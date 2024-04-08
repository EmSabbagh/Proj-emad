<script setup>
import axios from 'axios'
import {ref} from 'vue'

const eventList =ref([])

function getEvent(){
  axios.get('http://localhost:8080/culture/backlinks/list').then((res)=>{
    eventList.value = res.data.rows
  })
}



getEvent()
</script>

<template>

  <header>
    <div class="container_wrapper">
      <div class="logo">
        <img src="./assets/images/logo_2020.png" alt="">
      </div>
      <div class="right">
        <div><img src="./assets/images/logo2_2020.png" alt=""></div>

      </div>
    </div>
    <nav>
      <ul>
        <li><router-link to="/">Home</router-link></li>
      </ul>
    </nav>
  </header>

  <RouterView />

  <footer>
    <div style="height: 200px;width: 90%;margin: 0 auto;padding: 20px;" class="footer_wrapper">
      <div class="left">
        <div class="left-item">
          <div class="label">emailAddress:</div>
          <div class="text">Istanbulevent@ief.com</div>
        </div>
        <div class="left-item">
          <div class="label">phone:</div>
          <div class="text">123456789</div>
        </div>

      </div>
      <div class="right">
          <ul>
            <li v-for="(item,index) in eventList"><a :href="item.linlk">{{ item.name }}</a></li>
          </ul>
      </div>
    </div>

  </footer>
</template>

<style scoped>
footer {
  width: 100%;
  background-color: rgba(255, 125, 3, 0.775);
}

.left{
  width: 300px;
  height: 150px;
  background-color: rgba(255, 123, 0, 0.216);
  padding: 20px;
}

footer .right{
  width: 300px;
  height: 150px;
  background-color: rgba(255, 123, 0, 0.216);
  padding: 20px;
}

header {
  background-color: rgb(246, 143, 48);
}

.container_wrapper {
  width: 90%;
  height: 120px;
  margin: 0 auto;
  background-color: rgb(246, 143, 48);
  display: flex;
  justify-content: space-between;
  align-items: center;

}


nav {
  background-color: rgb(246, 143, 48);
  width: 90%;
  margin: 0 auto;
}

nav ul {
  display: flex;
  justify-content: center;
  list-style: none;
  padding: 10px 0;
}

nav ul li {
  margin: 0 20px;
}

nav ul li a {
  color: white;
  text-decoration: none;
  font-size: 24px;
}

nav ul li a:hover {
  color: black;
}

.left-item{
  display: flex;
  margin-bottom: 20px;
}

.left-item .label{
  color: #fff;
}

.left-item .text{
  padding-left: 10px;
  color: #fff;
}

.footer_wrapper{
  display: flex;
  justify-content: space-between;
}

.right ul{
  list-style: none;

}
.right a{
  color: #fff;
  text-decoration: none;
  cursor: pointer;
}

.right a:hover{
  color: black;
}


</style>
