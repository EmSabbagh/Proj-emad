<script>
import { onMounted, ref } from 'vue'
import router from '@/router'
import axios from 'axios'

export default {
  setup() {
    const detail = ref({})

    onMounted(() => {
      const id = router.currentRoute.value.query.id
      
      axios.get(`http://localhost:8080/culture/activity/${id}`).then((res) => {
        detail.value = res.data.data
      })
    })

    return {
      detail
    }
  }
}
</script>

<template>
    <div class="eventDetail">
        <h2>{{ detail.name }}</h2>
      
        <div class="info">
            <div>
                <span>activityTime：</span>
                <span>{{ detail.activityTime }}</span>
            </div>
            <div>
                <span>address：</span>
                <span>{{ detail.place }}</span>
            </div>
        </div>
       
        <div v-html="detail.content"></div>
        

        <div class="join">
            <div class="modulename">Participants:</div>
            <div class="joinList">
                    <div class="joinName">JACK</div>
                    <div class="joinName">MAK</div>
                   
            </div>
        </div>

    </div>
</template>

<style scoped>
.eventDetail{
    background: #fff;
    min-height: calc(100vh - 260px);
    width: 90%;
    margin: 40px auto;
    background-color: #fff;
    padding: 40px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0,0,0,0.1);
    min-height: 400px;
}

.title{
    text-align: center;
    margin-top: 20px;
}

.info{
    margin: 20px;
    display: flex;
    justify-content: center;
}



.value{
    margin-left: 10px;
}

.label-item{
    margin-right: 20px;
}

.join{
    margin-top: 40px;
}

.modulename{
    font-size: 20px;
    margin-bottom: 20px;
    color: #bd1a2d;
}

.joinList{
    display: flex;
    
}

.joinName{
    margin-right: 20px;
}

h2{
    text-align: center;
}
</style>