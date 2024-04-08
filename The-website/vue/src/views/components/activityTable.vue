
<script  setup>
import axios from 'axios'
import {ref} from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const tableData = ref([])
function getActive(){
axios.get('http://localhost:8080/culture/activity/list').then((res)=>{
  tableData.value = res.data.rows
})
}

const getActiveDetail = (id) => {
  router.push({
    path: '/activeDetail',
    query: {
      id: id
    }
  })
}

function rowclick(row){
  getActiveDetail(row.id)
}



getActive()
</script>


<template>
    <el-table :data="tableData" stripe style="width: 100%" @row-click="rowclick">
      <el-table-column prop="id" label="Id" width="180" />
      <el-table-column prop="name" label="Name" width="180" />
      <el-table-column prop="place" label="Date" width="180" />
      <el-table-column prop="activityTime" label="activityTime" />
      <el-table-column prop="publishTime" label="publishTime" />
    </el-table>
  </template>
  
