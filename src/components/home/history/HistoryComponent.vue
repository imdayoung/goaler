<script setup>
import HistoryDataComponent from './HistoryDataComponent.vue';
import { ref, onMounted } from 'vue';
import { useAccountHistory } from '@/stores/accounthistory';

const accountHistoryStore = useAccountHistory();

const historyData = ref([]);

const fetchAccountHistories = async() => {
  await accountHistoryStore.getAllHistories(1);
  historyData.value = accountHistoryStore.accountHistories.slice().sort((a, b) => new Date(b.created_at) - new Date(a.created_at));
}

onMounted(() => {
  fetchAccountHistories()
});
</script>

<template>
  <div class="card mx-3 py-2 mb-3">
    <div v-for="(data, index) in historyData" class="container">
      <HistoryDataComponent :history="data" :index="index" :count="historyData.length"/>
    </div>
  </div>
</template>

<style scoped>

</style>
