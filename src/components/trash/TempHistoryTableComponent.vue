<script setup>
const props = defineProps({
    historyData: {
        type: Object,
        required: true
    }
});

const emit = defineEmits(['rowSelected']);

const selectDetail = (history) => {
    emit('rowSelected', history);
}
</script>

<template>
<div>
    <table class="table">
        <thead class="text-center">
        <tr>
            <th>날짜</th>
            <th>분류</th>
            <th>카테고리</th>
            <th>금액</th>
            <th>내역</th>
            <th>자산</th>
        </tr>
        </thead>
        <tbody>
            <tr v-for="(history, index) in historyData" :key="index" @click="selectDetail(history)">
            <td>{{ history.date }}</td>
            <td class="text-center">{{ history.type }}</td>
            <td class="text-center">{{ history.category }}</td>
            <td :class="{'text-primary': history.type == '수입', 'text-danger': history.type == '지출'}">{{ history.amount }}원</td>
            <td>{{ history.title }}</td>
            <td>{{ history.source }}</td>
            </tr>
        </tbody>
    </table>
    </div>
</template>

<style scoped>
.tr:hover {
    color: red;
    background-color: red;
}
.table th:first-child, .table td:first-child {
  width: 12%;
}

.table th:nth-child(2), .table td:nth-child(2) {
  width: 12%;
}

.table th:nth-child(3), .table td:nth-child(3) {
  width: 12%;
}

.table th:nth-child(4), .table td:nth-child(4) {
  width: 10%;
}

.table th:nth-child(5), .table td:nth-child(5) {
  width: 30%;
}

.table th:nth-child(6), .table td:nth-child(6) {
  width: 24%;
}
</style>
