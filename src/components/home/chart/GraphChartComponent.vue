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

// Chart.js의 모든 요소를 등록
Chart.register(...registerables);

const lineChart = ref(null);
const selectedYear = ref(new Date().getFullYear());
const selectedMonth = ref(new Date().getMonth() + 1);
const availableYears = ref([new Date().getFullYear()]);
const availableMonths = ref(Array.from({ length: 12 }, (_, i) => i + 1));

// 임시 데이터
const mockSpendingData = [
{ id: 1, title: '엽떡', date: '2024-08-01', money: 12000, type: 1 },
{ id: 2, title: '택시', date: '2024-08-02', money: 6000, type: 2 },
{ id: 3, title: '전시회', date: '2024-08-03', money: 10000, type: 1 },
{ id: 4, title: '영화', date: '2024-08-04', money: 18000, type: 2 },
{ id: 5, title: '과자', date: '2024-08-05', money: 3000, type: 1 },
{ id: 6, title: '청바지', date: '2024-08-06', money: 27000, type: 2 },
{ id: 7, title: '다이소', date: '2024-08-07', money: 4000, type: 2 },
{ id: 8, title: '마라탕', date: '2024-08-08', money: 9000, type: 1 },
];

// 현재 월의 전체 일수를 계산하는 함수
const getDaysInMonth = (year, month) => {
return new Date(year, month, 0).getDate();
};

// 일별 라벨을 생성하는 함수
const generateDailyLabels = (year, month) => {
const daysInMonth = getDaysInMonth(year, month);
return Array.from({ length: daysInMonth }, (_, i) => `${i + 1}일`);
};

// 수입 및 지출 데이터를 가져오는 함수
const fetchDataByType = (year, month, type) => {
const filteredData = mockSpendingData.filter((item) => {
    const itemDate = new Date(item.date);
    return (
    itemDate.getFullYear() === year &&
    itemDate.getMonth() + 1 === month &&
    item.type === type
    );
});
return generateDailyData(filteredData, year, month);
};

// 일별 데이터를 생성하는 함수
const generateDailyData = (data, year, month) => {
const daysInMonth = getDaysInMonth(year, month);
const dailyData = Array(daysInMonth).fill(0);
data.forEach((item) => {
    const day = new Date(item.date).getDate() - 1; // 일자를 가져와서 해당 일자의 데이터 누적
    dailyData[day] += item.money; // 해당 일자의 데이터 누적
});
return dailyData;
};

// 차트 업데이트 함수
const updateChart = async () => {
const incomeData = await fetchDataByType(selectedYear.value, selectedMonth.value, 1); // 수입 데이터 가져오기
const outcomeData = await fetchDataByType(selectedYear.value, selectedMonth.value, 2); // 지출 데이터 가져오기

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
        borderColor: '#8898da',
        fill: false,
        },
        {
        label: '지출',
        data: outcomeData,
        borderColor: '#d992c9',
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
    },
});
};

// 선택된 년도와 월이 변경될 때마다 차트 업데이트
watch([selectedYear, selectedMonth], updateChart);

// 초기 차트 설정
onMounted(() => {
updateChart();
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
