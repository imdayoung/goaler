import { createRouter, createWebHistory } from 'vue-router'
import HomeComponent from '@/components/HomeComponent.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/account-books/1'  // 루트 경로에서 /account-books/1로 리다이렉트
    },
    {
      path: '/account-books/:accountBookIdx',
      name: 'HomeComponent',
      component: HomeComponent,
      props: true  // 매개변수를 컴포넌트로 전달
    }
  ]
})

export default router
