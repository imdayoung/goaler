<script setup>
import PieChartComponent from './PieChartComponent.vue';
import GraphChartComponent from './GraphChartComponent.vue';
</script>

<template>
  <div class="charts-container my-3">
    <PieChartComponent/>
    <GraphChartComponent/>
  </div>
</template>

<style scoped>
.charts-container {
  display: flex;
  gap: 20px;
  justify-content: space-between;
  margin: 0;
}
</style>
