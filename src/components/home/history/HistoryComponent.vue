<script setup>
import HistoryDataComponent from './HistoryDataComponent.vue';

const historyData = [
  {
    dateTime: "2024-05-01 17:51:00",
    type: 1,
    amount: 20000,
    title: "김국민",
    category: "회비"
  },
  {
    dateTime: "2024-05-01 17:52:00",
    type: 2,
    amount: 68000,
    title: "국민닭갈비",
    category: "식비"
  },
  {
    dateTime: "2024-05-01 17:53:00",
    type: 1,
    amount: 10000,
    title: "최모임",
    category: "지각비"
  },
  {
    dateTime: "2024-05-01 17:51:00",
    type: 1,
    amount: 20000,
    title: "최모임",
    category: "회비"
  },
  {
    dateTime: "2024-05-01 17:52:00",
    type: 2,
    amount: 12000,
    title: "국민반점",
    category: "식비"
  },
  {
    dateTime: "2024-05-01 17:53:00",
    type: 1,
    amount: 30000,
    title: "최모임",
    category: "벌금"
  },
  {
    dateTime: "2024-05-01 17:51:00",
    type: 1,
    amount: 20000,
    title: "이비비",
    category: "회비"
  },
  {
    dateTime: "2024-05-01 17:52:00",
    type: 2,
    amount: 18000,
    title: "국민다방",
    category: "카페"
  }
];
historyData.sort((a, b) => new Date(b.dateTime) - new Date(a.dateTime));
</script>

<template>
  <div class="card mx-3 py-2 mb-3">
    <div v-for="(data, index) in historyData" class="container">
      <HistoryDataComponent :history="data" :index="index" :count="historyData.length"/>
    </div>
  </div>
</template>

<style scoped>
.card {
  width: 30%;
}
</style>
