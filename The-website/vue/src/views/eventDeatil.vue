<script>
import { onMounted, ref } from 'vue'
import router from '@/router'
import axios from 'axios'

export default {
  setup() {
    const detail = ref({})

    onMounted(() => {
      const id = router.currentRoute.value.query.id
      
      axios.get(`http://localhost:8080/culture/event/${id}`).then((res) => {
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
        <h2>{{ detail.title }}</h2>
       
        <div class="info">
            <div>
                <span>time：</span>
                <span>{{ detail.publishTime }}</span>
            </div>
            
        </div>
       
        <div v-html="detail.content"></div>
       

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
h2{
    text-align: center;
}

</style>