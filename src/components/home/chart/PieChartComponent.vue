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
import { ref, onMounted, computed } from 'vue';
import { Chart, registerables } from 'chart.js';
import { useCategoryStore } from '@/stores/category';
import { useAccountHistory } from '@/stores/accounthistory';

const categoryStore = useCategoryStore();
const accounthistoryStore = useAccountHistory();

const chart = ref(null);
const categories = ref({});
const selectedYear = ref(new Date().getFullYear());
const selectedMonth = ref(new Date().getMonth() + 1);

// Chart.js의 모든 요소를 등록
Chart.register(...registerables);

// 현재 달 데이터 필터링 함수
const filterCurrentMonthData = (data) => {
  return data.filter(item => {
    const itemDate = new Date(item.createdAt);
    return itemDate.getFullYear() === selectedYear.value && itemDate.getMonth() + 1 === selectedMonth.value;
  });
};

// 카테고리별 데이터 그룹화 함수
const groupByCategory = (data) => {
  return data.reduce((acc, item) => {
    acc[item.categoryIdx] = (acc[item.categoryIdx] || 0) + item.amount; // 가격을 합산
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
      labels: labels.map(idx => categories.value[idx]), // 실제 라벨 데이터
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
          display: false // 레전드 숨기기
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

const fetchData = async () => {
  await categoryStore.getCategories();
  await accounthistoryStore.getCurrentMonthExpenses(1);

  const comesData = accounthistoryStore.accountExpenses;
  const categoriesData = categoryStore.categories;

  // 현재 날짜의 연도와 월로 필터링
  selectedYear.value = new Date().getFullYear();
  selectedMonth.value = new Date().getMonth() + 1;

  // 데이터 필터링 및 그룹화
  const filteredData = filterCurrentMonthData(comesData);
  const categoryCounts = groupByCategory(filteredData);

  // 차트 데이터 준비
  const labels = Object.keys(categoryCounts);
  const counts = Object.values(categoryCounts);

  // category 데이터 매핑
  categories.value = categoriesData.reduce((acc, category) => {
    acc[category.idx] = category.name;
    return acc;
  }, {});

  // 차트 생성
  createChart(labels, counts);
};

onMounted(() => {
  fetchData();
});
</script>

<style scoped>
.card {
  width: 50%; /* 카드의 너비를 화면의 50%로 설정 */
  margin: 0; /* 카드의 좌우 여백을 제거 */
  background: #ffffff; /* 카드의 배경색 설정 */
  padding: 16px; /* 카드의 내부 여백 설정 */
  position: relative; /* 차트의 위치를 조정하기 위한 상대 위치 설정 */
  height: 400px; /* 카드의 높이 설정 (세로 폭을 일정하게 하기 위함) */
}

.header {
  display: flex;
  align-items: center;
  margin-bottom: 16px;
}

.header i {
  font-size: 20px; /* 아이콘 크기 설정 */
  margin-right: 8px; /* 아이콘과 텍스트 간격 설정 */
}

.header h5 {
  margin: 0; /* 텍스트의 상하 여백 제거 */
  font-size: 20px;
}

.chart-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%; /* 차트의 높이를 카드에 맞추기 위한 설정 */
}
</style>
