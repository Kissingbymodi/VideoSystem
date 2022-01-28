import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '后台视频管理', icon: 'dashboard' }
    }]
  },

  {
    path: '/user',
    component: Layout,
    redirect: '/user/index',
    name: 'User',
    meta: { title: '用户管理', icon: 'user' },
    children: [
      {
        path: 'index',
        name: 'user',
        component: () => import('@/views/user/index'),
        meta: { title: '用户列表', icon: 'list' }
      },
      {
        path: 'tree',
        name: 'Tree',
        component: () => import('@/views/tree/index'),
        meta: { title: '用户点击播放量', icon: 'tree' }
      }
    ]
  },
  {
    path: '/video',
    component: Layout,
    redirect: '/video/index',
    name: 'Video',
    meta: { title: '视频管理', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'index',
        name: 'video',
        component: () => import('@/views/video/index'),
        meta: { title: '视频列表', icon: 'list' }
      },
      {
        path: 'tree',
        name: 'Tree',
        component: () => import('@/views/undervideo/index'),
        meta: { title: '审核视频', icon: 'guide' }
      }
    ]
  },
  {
    path: '/addvideo',
    hidden: true,
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'addvideo',
        component: () => import('@/views/addvideo/index'),
        meta: { title: '添加视频', icon: 'form' }
      }
    ]
  },
  {
    path: '/category',
    component: Layout,
    redirect: '/category/index',
    meta: { title: '分类管理', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'index',
        name: 'category',
        component: () => import('@/views/category/index'),
        meta: { title: '分类管理', icon: 'form' }
      }
    ]
  },
  {
    path: '/addcategory',
    hidden: true,
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'addcategory',
        component: () => import('@/views/addcategory/index'),
        meta: { title: '添加分类', icon: 'form' }
      }
    ]
  },
  {
    path: '/editcategory',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'index/:id',
        name: 'editcategory',
        component: () => import('@/views/editcategory/index'),
        meta: { title: '编辑分类', icon: 'form' }
      }
    ]
  },
  {
    path: '/adduser',
    hidden: true,
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'adduser',
        component: () => import('@/views/adduser/index'),
        meta: { title: '添加用户', icon: 'form' }
      }
    ]
  },
  {
    path: '/edituser',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'index/:id',
        name: 'edituser',
        component: () => import('@/views/edituser/index'),
        meta: { title: '编辑用户', icon: 'form' }
      }
    ]
  },
  {
    path: '/editvideo',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'index/:id',
        name: 'editvideo',
        component: () => import('@/views/editvideo/index'),
        meta: { title: '编辑视频', icon: 'form' }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
