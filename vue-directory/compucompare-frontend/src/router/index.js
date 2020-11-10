import Vue from 'vue'
import VueRouter from 'vue-router'
import DeviceRecommendations from "../views/DeviceRecommendations.vue"
import Dashboard from "../views/Dashboard.vue"

Vue.use(VueRouter)

const routes = [
  {
    path: '/DeviceRecommendations',
    name: 'DeviceRecommendations',
    component: DeviceRecommendations
  },
  {
    path: '/',
    name: 'Dashboard',
    component: Dashboard
  },

]

const router = new VueRouter({
  routes
})

export default router