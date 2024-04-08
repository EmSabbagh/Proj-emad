<script setup>
import activityTable from './components/activityTable.vue';
import Gallery from './Gallery.vue';
import axios from 'axios'
import {ref} from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const eventList =ref([])
// 获取特色事件
function getEvent(){
  axios.get('http://localhost:8080/culture/event/list').then((res)=>{
    eventList.value = res.data.rows
  })
}

getEvent()

const getEventDetail = (id) => {
  router.push({
    path: '/eventDetail',
    query: {
      id: id
    }
  })
}

</script>

<template>
  <div class="home-page">
    <div class="home-info-card">
      <div class="carousel">
        <el-carousel indicator-position="outside" arrow="always" height="380px">
          <el-carousel-item>
            <img src="../assets/images/car1.png" alt="">
          </el-carousel-item>
          <el-carousel-item>
            <img src="../assets/images/car2.png" alt="">
          </el-carousel-item>
          <el-carousel-item>
            <img src="../assets/images/car3.png" alt="">
          </el-carousel-item>
        </el-carousel>
      </div>
      <div class="event">
        <div style="display: flex;justify-content: space-between;">
          <h2>News Event</h2>
          <span style="color: #666;">More</span>
        </div>

        <div class="event-list">
          <div class="event-item-wrapper" v-for="(item,index) in eventList" @click="getEventDetail(item.id).slice(0,4)">
            <div class="event-item">
              <div class="event-title">{{ item.title }}</div>
              <div class="event-time">{{ item.publishTime }}</div>
            </div>
            <el-divider />
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="activity">
    <div class="activity_wrapper">
        <div class="module-name">News activity</div>
      <div class="activity-list">
            <activityTable></activityTable>
      </div>
    </div>
  </div>
  <Gallery></Gallery>
  
</template>
<style scoped>
.home-page {
  background: url('../assets/images/body_bg_2020.jpg') no-repeat center center;
  width: 100%;
  height: 600px;
  display: flex;
  align-items: center;
}

.home-info-card {
  width: 90%;
  height: 380px;
  background-color: rgb(255, 255, 255);
  margin: 0 auto;
  border-radius: 10px;
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.1);
  display: grid;
  grid-template-columns: 1fr 1fr;
  overflow: hidden;
}

@media screen and (max-width: 768px){
    .home-info-card {
        grid-template-columns: 1fr;
        height: 800px;
    }
    .carousel {
        height: 400px;
    }
    .home-page {
        height: 1000px;
    }
}

.carousel img {
  width: 100%;
  height: 380px;
}

.event {
  padding: 20px;
}

.event h2 {
  font-size: 24px;
  color: #fcad46;
}

.event-list {
  margin-top: 20px;
}

.event-item-wrapper {
  width: 90%;
  margin: 0 auto;

}

.event-item {
  display: flex;
  justify-content: space-between;
  flex-flow: row;
}

.event-title {
  font-size: 16px;
  color: #333;
}

.event-time {
  font-size: 14px;
  color: #666;
}

.activity {
    background-color: #f9f8f6;
    overflow: hidden;
    
}

.activity_wrapper {
    width: 90%;
    margin: 0 auto;
  
    padding: 20px;
  
}

.contactInfo {
    width: 100%;
    height: 100%;
    padding: 20px;
}

.module-name {
    font-size: 24px;
    color: #74afad;
    margin-bottom: 20px;
}


</style>