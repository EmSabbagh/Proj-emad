import request from '@/utils/request'

// 查询外链管理列表
export function listBacklinks(query) {
  return request({
    url: '/culture/backlinks/list',
    method: 'get',
    params: query
  })
}

// 查询外链管理详细
export function getBacklinks(id) {
  return request({
    url: '/culture/backlinks/' + id,
    method: 'get'
  })
}

// 新增外链管理
export function addBacklinks(data) {
  return request({
    url: '/culture/backlinks',
    method: 'post',
    data: data
  })
}

// 修改外链管理
export function updateBacklinks(data) {
  return request({
    url: '/culture/backlinks',
    method: 'put',
    data: data
  })
}

// del外链管理
export function delBacklinks(id) {
  return request({
    url: '/culture/backlinks/' + id,
    method: 'delete'
  })
}
