<!-- <script setup>
import { ref } from 'vue';
import HistoryDetailComponent from './HistoryDetailComponent.vue'
import HistoryTableComponent from './HistoryTableComponent.vue';

// todo: data 받아오기
const historyData = ref([
    {
        date: "2024-07-30",
        type: "지출",
        category: "식비",
        amount: 23000,
        title: "동대문엽기떡볶이건대직영점2호",
        source: "카드의정석 오하CHECK",
        memo: "건대 ㄷㄷ",
    },
    {
        date: "2024-07-30",
        type: "수입",
        category: "월급",
        amount: 316000,
        title: "고용노동부",
        source: "KB마이핏통장",
        memo: "",
    },
    {
        date: "2024-07-30",
        type: "수입",
        category: "금융소득",
        amount: 4500,
        title: "KB저축은행이자",
        source: "kiwi팡팡통장",
        memo: "",
    },
    {
        date: "2024-08-01",
        type: "지출",
        category: "식비",
        amount: 5000,
        title: "세종대학교학식학생회관",
        source: "우리 K-패스 (COOKIE CHECK)",
        memo: "치킨가라아게동",
    },
    {
        date: "2024-08-01",
        type: "수입",
        category: "금융소득",
        amount: 4500,
        title: "KB저축은행이자",
        source: "kiwi팡팡통장",
        memo: "",
    },
    {
        date: "2024-08-01",
        type: "지출",
        category: "식비",
        amount: 5000,
        title: "세종대학교학식학생회관",
        source: "우리 K-패스 (COOKIE CHECK)",
        memo: "치킨가라아게동",
    },
    {
        date: "2024-08-01",
        type: "수입",
        category: "금융소득",
        amount: 4500,
        title: "KB저축은행이자",
        source: "kiwi팡팡통장",
        memo: "",
    },
    {
        date: "2024-08-01",
        type: "지출",
        category: "식비",
        amount: 5000,
        title: "세종대학교학식학생회관",
        source: "우리 K-패스 (COOKIE CHECK)",
        memo: "치킨가라아게동",
    },
    {
        date: "2024-08-01",
        type: "수입",
        category: "금융소득",
        amount: 4500,
        title: "KB저축은행이자",
        source: "kiwi팡팡통장",
        memo: "",
    },
    {
        date: "2024-08-01",
        type: "지출",
        category: "식비",
        amount: 5000,
        title: "세종대학교학식학생회관",
        source: "우리 K-패스 (COOKIE CHECK)",
        memo: "치킨가라아게동",
    }
]);

const incomeData = historyData.value.filter(item => item.type === '수입');
const outcomeData = historyData.value.filter(item => item.type === '지출');

const today = new Date();

const selectedYear = ref(today.getFullYear());
const selectedMonth = ref(today.getMonth() + 1);

const selectedDetail = ref(null);

const closeDetail = () => {
    selectedDetail.value = null;
}

const toggleDetail = (data) => {
  selectedDetail.value = selectedDetail.value === data ? null : data;
};
</script>

<template>
  <div class="position-relative">
    <div class="card">
      <div class="selected-month mt-2">
        <button class="btn" type="button">
          <font-awesome-icon icon="fa-solid fa-chevron-left"/>
        </button>
        <span class="fs-6"><strong>{{ selectedYear }}년 {{ selectedMonth }}월</strong></span>
        <button class="btn" type="button">
          <font-awesome-icon icon="fa-solid fa-chevron-right"/>
        </button>
      </div>
      <div>
        <button class="btn this-month mx-3 mb-2" type="button">이번 달</button>
      </div>
      <div class="tab-container mt-3 mb-4 mx-3">
        <ul class="nav nav-tabs" role="tablist">
          <li class="nav-item" role="presentation">
            <button class="nav-link active all-history-tab" id="all-history-tab"
              data-bs-toggle="tab" data-bs-target="#all-history" type="button" role="tab"
              aria-controls="all-history" aria-selected="true">전체</button>
          </li>
          <li class="nav-item" role="presentation">
            <button class="nav-link income-tab" id="income-tab"
              data-bs-toggle="tab" data-bs-target="#income" type="button" role="tab"
              aria-controls="income" aria-selected="false">수입</button>
          </li>
          <li class="nav-item" role="presentation">
            <button class="nav-link outcome-tab" id="outcome-tab"
              data-bs-toggle="tab" data-bs-target="#outcome" type="button" role="tab"
              aria-controls="outcome" aria-selected="false">지출</button>
          </li>
        </ul>
        <div class="tab-content">
          <div class="tab-pane fade show active table-container" id="all-history" role="tabpanel" aria-labelledby="all-history-tab">
            <HistoryTableComponent :history-data="historyData" @row-selected="toggleDetail"/>
          </div>
          <div class="tab-pane fade table-container" id="income" role="tabpanel" aria-labelledby="income-tab">
            <HistoryTableComponent :history-data="incomeData" @row-selected="toggleDetail"/>
          </div>
          <div class="tab-pane fade table-container" id="outcome" role="tabpanel" aria-labelledby="outcome-tab">
            <HistoryTableComponent :history-data="outcomeData" @row-selected="toggleDetail"/>
          </div>
        </div>
      </div>
    </div>
    <div class="btn-container">
        <font-awesome-icon icon="circle-plus" class="btn-insert fa-4x"/>
    </div>

    <transition name="slide-fade">
      <HistoryDetailComponent v-if="selectedDetail" :history="selectedDetail" @close="closeDetail" />
    </transition>
  </div>
</template>

<style scoped>
/* 기존 스타일 유지 */
div {
    font-family: 'notoSansKR', 'notoSans';
    font-size: 0.8rem;
    color: #60584C;
}
.card {
    height: 500px;
    display: flex;
    flex-direction: column;
}
.text-center {
    text-align: center;
}
.table {
    cursor: pointer;
}
.position-relative {
    position: relative;
    overflow: hidden;
}
.slide-fade-enter-active, .slide-fade-leave-active {
    transition: transform 0.5s ease;
}
.slide-fade-enter-from {
    transform: translateX(100%);
}
.slide-fade-leave-to {
    transform: translateX(100%);
}
.btn-container {
    position: relative;
    margin-top: 10px;
    text-align: right;
}
.btn-insert {
    color: #FFBC00;
}
.selected-month {
    display: flex;
    justify-content: center;
    align-items: center;
}
.this-month {
    background-color: #60584C;
    color: white;
    font-size: 0.8rem;
}
.tab-container {
    flex-grow: 1;
    display: flex;
    flex-direction: column;
    overflow: hidden;
    padding: 0;
}
.tab-content {
    flex-grow: 1; /* tab-content가 남은 공간을 차지하도록 설정 */
    display: flex;
    flex-direction: column;
    overflow-y: auto; /* 내용이 많을 때 스크롤바 표시 */
}
/* .table-container {
    flex-grow: 1;
} */
.all-history-tab, .all-history-tab.active {
    color: #60584C;
}
.income-tab, .income-tab.active {
    color: blue;
}
.outcome-tab, .outcome-tab.active {
    color: red;
}
</style> -->
