<template>
  <div class="card">
    <div class="header">
      <i class="fa-solid fa-chart-line"></i>
      <h5>이번 달 수입/지출 분석 그래프</h5>
    </div>
    <div class="chart-container">
      <canvas ref="lineChart"></canvas>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import { Chart, registerables } from 'chart.js';
import { useRoute } from 'vue-router';
import { useAccountHistory } from '@/stores/accounthistory';

// Chart.js의 모든 요소를 등록
Chart.register(...registerables);

const lineChart = ref(null);
const selectedYear = ref(new Date().getFullYear());
const selectedMonth = ref(new Date().getMonth() + 1);
const accountHistoryStore = useAccountHistory();

const route = useRoute();
const accountBookIdx = ref(route.params.accountBookIdx);

// 현재 월의 전체 일수를 계산하는 함수
const getDaysInMonth = (year, month) => {
  return new Date(year, month, 0).getDate();
};

// 일별 라벨을 생성하는 함수
const generateDailyLabels = (year, month) => {
  const daysInMonth = getDaysInMonth(year, month);
  return Array.from({ length: daysInMonth }, (_, i) => `${i + 1}일`);
};

// 일별 데이터를 생성하는 함수
const generateDailyData = (data, daysInMonth) => {
  const dailyData = Array.from({ length: daysInMonth }, () => 0);
  data.forEach((item) => {
    const day = new Date(item.createdAt).getDate() - 1; // 일자를 가져와서 해당 일자의 데이터 누적
    dailyData[day] += item.amount; // 해당 일자의 데이터 누적
  });
  return dailyData;
};

// 수입 데이터를 가져오는 함수
const fetchIncomeData = async () => {
  if (accountBookIdx.value) {
    await accountHistoryStore.getCurrentMonthIncomes(accountBookIdx.value);
    const incomeData = accountHistoryStore.accountIncomes;
    const daysInMonth = getDaysInMonth(selectedYear.value, selectedMonth.value);
    return generateDailyData(incomeData, daysInMonth);
  }
  return [];
};

// 지출 데이터를 가져오는 함수
const fetchExpenseData = async () => {
  if (accountBookIdx.value) {
    await accountHistoryStore.getCurrentMonthExpenses(accountBookIdx.value);
    const expenseData = accountHistoryStore.accountExpenses;
    const daysInMonth = getDaysInMonth(selectedYear.value, selectedMonth.value);
    return generateDailyData(expenseData, daysInMonth);
  }
  return [];
};

// 차트 업데이트 함수
const updateChart = async () => {
  const incomeData = await fetchIncomeData(); // 수입 데이터 가져오기
  const outcomeData = await fetchExpenseData(); // 지출 데이터 가져오기

  const ctx = lineChart.value.getContext('2d');
  if (lineChart.value.chart) {
    lineChart.value.chart.destroy();
  }

  lineChart.value.chart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: generateDailyLabels(selectedYear.value, selectedMonth.value), // 일별 라벨 생성 함수
      datasets: [
        {
          label: '수입',
          data: incomeData,
          borderColor: '#FFBC00',
          fill: false,
        },
        {
          label: '지출',
          data: outcomeData,
          borderColor: '#838687',
          fill: false,
        },
      ],
    },
    options: {
      responsive: true,
      animation: {
        duration: 0
      },
      scales: {
        x: {
          display: true,
          title: {
            display: true,
            text: '일자',
          },
        },
        y: {
          display: true,
          title: {
            display: true,
            text: '금액',
          },
        },
      },
      plugins: {
        legend: {
          display: false,
        },
        datalabels: {
          display: false,
        }
      }
    },
  });
};

// URL의 accountBookIdx가 변경될 때마다 차트 업데이트
watch(() => route.params.accountBookIdx, (newIdx) => {
  if (newIdx) {
    accountBookIdx.value = newIdx;
    updateChart();
  }
});

// 초기 차트 설정
onMounted(() => {
  updateChart();
});
</script>

<style scoped>
.card {
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
