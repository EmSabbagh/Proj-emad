import request from '@/utils/request'

// 查询特色事件列表
export function listEvent(query) {
  return request({
    url: '/culture/event/list',
    method: 'get',
    params: query
  })
}

// 查询特色事件详细
export function getEvent(id) {
  return request({
    url: '/culture/event/' + id,
    method: 'get'
  })
}

// 新增特色事件
export function addEvent(data) {
  return request({
    url: '/culture/event',
    method: 'post',
    data: data
  })
}

// 修改特色事件
export function updateEvent(data) {
  return request({
    url: '/culture/event',
    method: 'put',
    data: data
  })
}

// del特色事件
export function delEvent(id) {
  return request({
    url: '/culture/event/' + id,
    method: 'delete'
  })
}
