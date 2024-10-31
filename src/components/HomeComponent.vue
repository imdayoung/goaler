<script setup>
import EmptyComponent from '@/components/EmptyComponent.vue'
import ChatBotComponent from '@/components/home/chatbot/ChatBotComponent.vue'
import GoalComponent from '@/components/home/goal/GoalComponent.vue'
import AnalysisComponent from '@/components/home/goal/AnalysisComponent.vue'
import HistoryComponent from '@/components/home/history/HistoryComponent.vue'
import AccountBookComponent from '@/components/home/history/AccountBookComponent.vue'
import DoughnutChartComponent from '@/components/home/chart/DoughnutChartComponent.vue'
import GraphChartComponent from '@/components/home/chart/GraphChartComponent.vue'
</script>

<template>
  <div class="container">
    <ChatBotComponent/>
    <EmptyComponent/>
    <div class="component-container"><GoalComponent/></div>
    <div class="component-container"></div>
    <div class="component-container">
      <div class="main-component-container my-3">
        <div class="history-component">
          <AccountBookComponent/>
          <HistoryComponent/>
        </div>
        <div class="other-components">
          <AnalysisComponent/>
          <DoughnutChartComponent/>
          <GraphChartComponent/>
        </div>
      </div>
    </div>
    <EmptyComponent/>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.component-container {
  width: 90%;
}
.main-component-container {
  display: flex;
  flex-direction: row;
  align-items: center;
  align-items: flex-start;
  gap: 1.2rem;
}
.history-component {
  width: 40%;
  display: flex;
  flex-direction: column;
  gap: 1.2rem;
}
.other-components {
  width: 60%;
  display: flex;
  flex-direction: column;
  gap: 1.2rem;
}
</style>
