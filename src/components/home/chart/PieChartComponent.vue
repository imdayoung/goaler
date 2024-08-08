<template>
  <div class="card">
    <div class="header">
      <i class="fa-solid fa-chart-pie"></i>
      <h5>이번 달 지출 카테고리별 그래프</h5>
    </div>
    <div class="chart-container">
      <canvas id="categoryChart"></canvas>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import { useRoute } from 'vue-router';
import { Chart, registerables } from 'chart.js';
import { useCategoryStore } from '@/stores/category';
import { useAccountHistory } from '@/stores/accounthistory';

// Chart.js의 모든 요소를 등록
Chart.register(...registerables);

const route = useRoute();
const accountBookIdx = ref(route.params.accountBookIdx);

const categoryStore = useCategoryStore();
const accounthistoryStore = useAccountHistory();
const chart = ref(null);
const categories = ref({});
const selectedYear = ref(new Date().getFullYear());
const selectedMonth = ref(new Date().getMonth() + 1);

// 현재 월의 데이터를 필터링하는 함수
const filterCurrentMonthData = (data) => {
  return data.filter(item => {
    const itemDate = new Date(item.createdAt);
    return itemDate.getFullYear() === selectedYear.value && itemDate.getMonth() + 1 === selectedMonth.value;
  });
};

// 카테고리별로 데이터를 그룹화하는 함수
const groupByCategory = (data) => {
  return data.reduce((acc, item) => {
    acc[item.categoryIdx] = (acc[item.categoryIdx] || 0) + item.amount;
    return acc;
  }, {});
};

// 차트 생성 함수
const createChart = (labels, counts) => {
  if (chart.value) {
    chart.value.destroy();
  }
  const ctx = document.getElementById('categoryChart').getContext('2d');
  chart.value = new Chart(ctx, {
    type: 'pie',
    data: {
      labels: labels.map(idx => categories.value[idx]),
      datasets: [{
        data: counts,
        backgroundColor: [
          '#FFBC00',
          '#838687',
          '#94C70F',
          '#816843',
          '#4A483F',
          '#4E96D8',
          '#F25D83',
        ]
      }]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      elements: {
        arc: {
          borderWidth: 1
        }
      },
      animation: {
        duration: 0
      },
      plugins: {
        legend: {
          display: false
        },
        tooltip: {
          callbacks: {
            label: function(context) {
              const label = context.label || '';
              const value = context.raw;
              return ` ${value.toLocaleString()} 원`;
            }
          }
        }
      }
    }
  });
};

// 데이터 가져오기 함수
const fetchData = async () => {
  console.log('Fetching data for accountBookIdx:', accountBookIdx.value);  // 디버깅 로그 추가
  if (!accountBookIdx.value) return; // accountBookIdx가 없으면 함수 종료

  await categoryStore.getCategories();
  await accounthistoryStore.getCurrentMonthExpenses(accountBookIdx.value);

  const expenseData = accounthistoryStore.accountExpenses;
  const categoriesData = categoryStore.categories;

  selectedYear.value = new Date().getFullYear();
  selectedMonth.value = new Date().getMonth() + 1;

  const filteredData = filterCurrentMonthData(expenseData);
  const categoryCounts = groupByCategory(filteredData);

  const labels = Object.keys(categoryCounts);
  const counts = Object.values(categoryCounts);

  categories.value = categoriesData.reduce((acc, category) => {
    acc[category.idx] = category.name;
    return acc;
  }, {});

  createChart(labels, counts);
};

// 컴포넌트가 마운트될 때와 URL의 accountBookIdx가 변경될 때 데이터 가져오기
onMounted(() => {
  console.log('Mounted ChartComponent with accountBookIdx:', accountBookIdx.value);  // 디버깅 로그 추가
  fetchData();
});

watch(() => route.params.accountBookIdx, (newIdx) => {
  console.log('accountBookIdx changed to:', newIdx);  // 디버깅 로그 추가
  accountBookIdx.value = newIdx;
  fetchData();
});
</script>

<style scoped>
.card {
  margin: 0;
  background: #ffffff;
  padding: 16px;
  position: relative;
  height: 400px;
}

.header {
  display: flex;
  align-items: center;
  margin-bottom: 16px;
}

.header i {
  font-size: 20px;
  margin-right: 8px;
}

.header h5 {
  margin: 0;
  font-size: 20px;
}

.chart-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
</style>
