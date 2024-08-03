<script setup>
import EmptyComponent from '@/components/EmptyComponent.vue'
import ChatBotComponent from '@/components/home/chatbot/ChatBotComponent.vue'
import GoalComponent from '@/components/home/goal/GoalComponent.vue'
import AnalysisComponent from '@/components/home/goal/AnalysisComponent.vue'
import HistoryMainComponent from '@/components/home/history/HistoryMainComponent.vue'
import ChartComponent from '@/components/home/chart/ChartComponent.vue'
</script>

<template>
  <div class="container">
    <ChatBotComponent/>
    <EmptyComponent/>
    <div class="component-container"><GoalComponent/></div>
    <div class="component-container"><AnalysisComponent/></div>
    <div class="component-container"><HistoryMainComponent/></div>
    <div class="component-container"><ChartComponent/></div>
    <EmptyComponent/>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center
}
.component-container {
  width: 80%;
}
</style>
