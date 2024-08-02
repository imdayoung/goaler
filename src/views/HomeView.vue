<script setup>
import GoalComponent from '@/components/home/GoalComponent.vue'
import ExplainComponent from '@/components/home/ExplainComponent.vue'
import ChatBotComponent from '@/components/home/ChatBotComponent.vue'
import PieChartComponent from '@/components/home/PieChartComponent.vue'
import GraphChartComponent from '@/components/home/GraphChartComponent.vue'
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
/* .sidebar.show {
  transform: translateX(0);
} */
</style>
