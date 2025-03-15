import request from '@/utils/request'

// 查询车辆信息列表
export function listCar(query) {
  return request({
    url: '/car/car/list',
    method: 'get',
    params: query
  })
}

// 查询车辆信息详细
export function getCar(vehicleId) {
  return request({
    url: '/car/car/' + vehicleId,
    method: 'get'
  })
}

// 新增车辆信息
export function addCar(data) {
  return request({
    url: '/car/car',
    method: 'post',
    data: data
  })
}

// 修改车辆信息
export function updateCar(data) {
  return request({
    url: '/car/car',
    method: 'put',
    data: data
  })
}

// 删除车辆信息
export function delCar(vehicleId) {
  return request({
    url: '/car/car/' + vehicleId,
    method: 'delete'
  })
}
