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

const categoryStore = useCategoryStore();

const chart = ref(null);
const categories = ref({});
const selectedYear = ref(new Date().getFullYear());
const selectedMonth = ref(new Date().getMonth() + 1);

// Chart.js의 모든 요소를 등록
Chart.register(...registerables);

// 현재 달 데이터 필터링 함수
const filterCurrentMonthData = (data) => {
  return data.filter(item => {
    const itemDate = new Date(item.date);
    return itemDate.getFullYear() === selectedYear.value && itemDate.getMonth() + 1 === selectedMonth.value;
  });
};

// 카테고리별 데이터 그룹화 함수
const groupByCategory = (data) => {
  return data.reduce((acc, item) => {
    acc[item.category_idx] = (acc[item.category_idx] || 0) + item.price; // 가격을 합산
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
          '#8898DA',
          '#D992C9',
          '#E2AEB1',
          '#E2D1AB',
          '#E6E7BC',
          '#A8C46E',
          '#B2BAC9',
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

// 임시 데이터
const mockSpendingData = [
  { id: 1, title: '엽떡', date: '2024-08-01', price: 12000, category_idx: 1 },
  { id: 2, title: '택시', date: '2024-08-02', price: 6000, category_idx: 2 },
  { id: 3, title: '전시회', date: '2024-08-03', price: 10000, category_idx: 3 },
  { id: 4, title: '영화', date: '2024-08-04', price: 18000, category_idx: 3 },
  { id: 5, title: '과자', date: '2024-08-05', price: 3000, category_idx: 4 },
  { id: 6, title: '청바지', date: '2024-08-06', price: 27000, category_idx: 5 },
  { id: 7, title: '다이소', date: '2024-08-07', price: 4000, category_idx: 6 },
  { id: 8, title: '마라탕', date: '2024-08-08', price: 9000, category_idx: 1 },
];

const fetchData = async () => {
  await categoryStore.getCategories();
  const comesData = mockSpendingData;
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
