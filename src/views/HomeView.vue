<script setup>
import GoalComponent from '@/components/home/goal/GoalComponent.vue'
import ExplainComponent from '@/components/home/goal/ExplainComponent.vue'
import ChatBotComponent from '@/components/home/chatbot/ChatBotComponent.vue'
import PieChartComponent from '@/components/home/chart/PieChartComponent.vue'
import GraphChartComponent from '@/components/home/chart/GraphChartComponent.vue'
import SideBarComponent from '@/components/sidebar/SideBarComponent.vue';
import HistoryMainComponent from '@/components/home/history/HistoryMainComponent.vue'
import TopbarComponent from '@/components/topbar/TopbarComponent.vue'

import { ref } from 'vue';

const isSidebarVisible = ref(false);

const showSidebar = () => {
  isSidebarVisible.value = true;
}

const hideSidebar = () => {
  isSidebarVisible.value = false;
}
</script>

<template>
  <TopbarComponent/>
  <ChatBotComponent/>
  <GoalComponent/>
  <ExplainComponent/>
  <HistoryMainComponent/>
  <div class="home" @click="hideSidebar">
    <button class="btn btn-primary" @mouseenter="showSidebar">Hover to Show Sidebar</button>
    <SideBarComponent v-model:isVisible="isSidebarVisible"/>
  </div>
  <div class="charts-container">
    <PieChartComponent/>
    <GraphChartComponent/>
  </div>
</template>

<style scoped>
div{
  font-family: 'notoSansKr', 'notoSans';
  color: #60584C;
}
.home {
  position: relative;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
.charts-container {
  display: flex; /* Flexbox 사용하여 자식 요소를 나란히 배치 */
  gap: 20px; /* 카드 사이의 간격 설정 */
  padding: 20px; /* 전체 컨테이너의 내부 여백 설정 */
  justify-content: space-between; /* 자식 요소들 사이의 공간을 균등하게 배분 */
}
/* .sidebar.show {
  transform: translateX(0);
} */
</style>
