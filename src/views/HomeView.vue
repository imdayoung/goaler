<script setup>
import TheWelcome from '../components/TheWelcome.vue'
import GoalComponet from '../components/home/GoalComponent.vue'
import ExplainComponent from '../components/home/ExplainComponent.vue'
import ChatBotComponent from '../components/home/ChatBotComponent.vue'
import PieChartComponent from '../components/home/PieChartComponent.vue'
import GraphChartComponent from '../components/home/GraphChartComponent.vue'
import HistoryComponent from '../components/home/HistoryComponent.vue'
import SideBarComponent from '../components/sidebar/SideBarComponent.vue';
import { ref } from 'vue';

const showSidebar = ref(false);

const hideSidebar = () => {
  setTimeout(() => {
    if (!document.querySelector('.offcanvas:hover')) {
      showSidebar.value = false;
    }
  }, 300); // Delay to allow hover effect on sidebar
};
</script>

<template>
  <div>
    <!-- 버튼에 hover 시 사이드바 표시 -->
    <button
      class="btn btn-primary"
      @mouseenter="showSidebar = true"
      @mouseleave="hideSidebar"
    >
      Hover me
    </button>

    <!-- 사이드바 컴포넌트 -->
    <SideBarComponent v-if="showSidebar" @close-sidebar="showSidebar = false" />
  </div>
</template>
