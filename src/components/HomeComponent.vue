<script setup>
import GoalComponent from '@/components/home/goal/GoalComponent.vue'
import ExplainComponent from '@/components/home/goal/ExplainComponent.vue'
import ChatBotComponent from '@/components/home/chatbot/ChatBotComponent.vue'
import PieChartComponent from '@/components/home/chart/PieChartComponent.vue'
import GraphChartComponent from '@/components/home/chart/GraphChartComponent.vue'
import HistoryMainComponent from '@/components/home/history/HistoryMainComponent.vue'
</script>

<template>
  <ChatBotComponent/>
  <GoalComponent/>
  <ExplainComponent/>
  <HistoryMainComponent/>
  <div class="charts-container">
    <PieChartComponent/>
    <GraphChartComponent/>
  </div>
</template>

<style scoped>
.charts-container {
  display: flex;
  gap: 20px;
  padding: 20px;
  justify-content: space-between;
  
}
</style>
