import request from '@/utils/request'

// 查询指标库列表
export function listKpi(query) {
  return request({
    url: '/assess/kpi/list',
    method: 'get',
    params: query
  })
}

// 查询指标库详细
export function getKpi(kpiId) {
  return request({
    url: '/assess/kpi/' + kpiId,
    method: 'get'
  })
}

// 新增指标库
export function addKpi(data) {
  return request({
    url: '/assess/kpi',
    method: 'post',
    data: data
  })
}

// 修改指标库
export function updateKpi(data) {
  return request({
    url: '/assess/kpi',
    method: 'put',
    data: data
  })
}

// 删除指标库
export function delKpi(kpiId) {
  return request({
    url: '/assess/kpi/' + kpiId,
    method: 'delete'
  })
}
