<script setup>
import HistoryDataComponent from './HistoryDataComponent.vue';
import { ref, onMounted, computed, watch } from 'vue';
import { useRoute } from 'vue-router';
import { useAccountHistory } from '@/stores/accounthistory';

const route = useRoute();
const accountHistoryStore = useAccountHistory();

const historyData = ref([]);
const paginatedData = ref([]);
const currentPage = ref(1);
const totalPages = ref(0);
const pageSize = ref(10);

// URL 파라미터에서 accountBookIdx를 추출
const accountBookIdx = ref(route.params.accountBookIdx);

const totalPagesArray = computed(() => {
  const total = totalPages.value;
  let start = Math.max(currentPage.value - 2, 1);
  let end = Math.min(start + 4, total);
  
  if (end - start < 4) {
    start = Math.max(end - 4, 1);
  }

  return Array.from({ length: end - start + 1 }, (_, i) => start + i);
});

const fetchAccountHistories = async (idx) => {
  await accountHistoryStore.getAllHistories(idx);
  historyData.value = accountHistoryStore.accountHistories;
  totalPages.value = Math.ceil(historyData.value.length / pageSize.value);
  bindData();
}

const bindData = () => {
  const start = (currentPage.value - 1) * pageSize.value;
  const end = start + pageSize.value;
  paginatedData.value = historyData.value.slice(start, end);
}

const changePage = (page) => {
  if (page >= 1 && page <= totalPages.value) {
    currentPage.value = page;
    bindData();
  }
}

// 데이터 로딩 함수 호출
const loadData = () => {
  if (accountBookIdx.value) {
    fetchAccountHistories(accountBookIdx.value);
  }
}

// onMounted와 watch를 통해 accountBookIdx가 변경될 때마다 데이터 새로고침
onMounted(() => {
  loadData();
});

watch(() => route.params.accountBookIdx, (newIdx) => {
  if (newIdx) {
    accountBookIdx.value = newIdx;
    loadData();
  }
});
</script>

<template>
  <div class="history-container">
    <div class="card">
      <div v-for="(data, index) in paginatedData" :key="data.id" class="container">
        <HistoryDataComponent :history="data" :index="index" :count="paginatedData.length"/>
      </div>
    </div>
    <nav aria-label="Page navigation">
      <ul class="pagination justify-content-center">
        <li class="page-item" :class="{ disabled: currentPage === 1 }">
          <button class="page-link" @click="changePage(currentPage - 1)" :disabled="currentPage === 1">
            <font-awesome-icon :icon="['fas', 'angle-left']" />
          </button>
        </li>
        <li v-for="page in totalPagesArray" :key="page" class="page-item" :class="{ active: currentPage === page }">
          <button class="page-link" @click="changePage(page)">{{ page }}</button>
        </li>
        <li class="page-item" :class="{ disabled: currentPage === totalPages }">
          <button class="page-link" @click="changePage(currentPage + 1)" :disabled="currentPage === totalPages">
            <font-awesome-icon :icon="['fas', 'angle-right']" />
          </button>
        </li>
      </ul>
    </nav>
  </div>
</template>

<style scoped>
.history-container {
  position: relative;
}
.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
  flex-wrap: wrap;
}
.page-link {
  display: flex;
  padding: 0.25rem 0.5rem;
  line-height: 1.2rem;
  border: 1px solid #ddd;
  color: #60584C;
  width: 1.9rem;
  justify-content: center;
  text-decoration: none;
  border-radius: 0.25rem;
  transition: background-color 0.2s, color 0.2s;
}
.page-link:hover {
  background-color: #60584C;
  color: white;
}
.page-item.active .page-link {
  background-color: #60584C;
  color: white;
  border-color: #60584C;
}
button {
  margin: 0 5px;
}
</style>
