<script setup>
import axios from 'axios'
import {ref} from 'vue'
import {onMounted} from 'vue'
const eventList =ref([])
function getImage(){
    axios.get('http://localhost:8080/culture/picture/list').then((res)=>{
        eventList.value = res.data.rows
    })
    console.log(eventList)
}

onMounted(()=>{
    getImage()
})

</script>

<template>
    <div class="gallery">
        <div class="model-name">Gallery</div>
        <el-carousel :interval="4000" type="card" height="300px">
            <el-carousel-item v-for="item in eventList" :key="item">
                <img :src="'http://localhost/dev-api'+item.addr" style="width: 100%; height: 300px">
                <!-- {{ item.addr }} -->
            </el-carousel-item>
        </el-carousel>
    </div>

</template>

<style scoped>
.el-carousel__item h3 {
    color: #475669;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
    text-align: center;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
}
.gallery {
    margin: 40px auto;
    width: 90%;
}

.model-name {
    font-size: 20px;
    margin: 20px 0;
    color: #bd1a2d;
}
</style>