import request from '@/utils/request'

// 查询画廊列表列表
export function listPicture(query) {
  return request({
    url: '/culture/picture/list',
    method: 'get',
    params: query
  })
}

// 查询画廊列表详细
export function getPicture(id) {
  return request({
    url: '/culture/picture/' + id,
    method: 'get'
  })
}

// 新增画廊列表
export function addPicture(data) {
  return request({
    url: '/culture/picture',
    method: 'post',
    data: data
  })
}

// 修改画廊列表
export function updatePicture(data) {
  return request({
    url: '/culture/picture',
    method: 'put',
    data: data
  })
}

// del画廊列表
export function delPicture(id) {
  return request({
    url: '/culture/picture/' + id,
    method: 'delete'
  })
}
