<script setup>
import { ref, onMounted, watch } from 'vue';
import { useRoute } from 'vue-router';
import { useAccountBookInfoStore } from '@/stores/accountbook';

const route = useRoute();
const accountBookInfoStore = useAccountBookInfoStore();
const accountBookName = ref("");
const accountBookNumber = ref("");
const accountBookBalance = ref("");

// 현재 `accountBookIdx`를 URL 파라미터에서 가져옵니다
const accountBookIdx = ref(route.params.accountBookIdx);

const fetchCurAccountBook = async (idx) => {
  await accountBookInfoStore.getAccountBookInfo(idx);
  accountBookName.value = accountBookInfoStore.curAccountBook.accountName;
  accountBookNumber.value = accountBookInfoStore.curAccountBook.accountNumber;
  accountBookBalance.value = parseInt(accountBookInfoStore.curAccountBook.balance, 10).toLocaleString();
};

// 컴포넌트가 마운트되면 데이터를 가져옵니다
onMounted(() => {
  if (accountBookIdx.value) {
    fetchCurAccountBook(accountBookIdx.value);
  }
});

// `accountBookIdx`가 변경될 때마다 데이터를 새로 가져옵니다
watch(() => route.params.accountBookIdx, (newIdx) => {
  if (newIdx) {
    accountBookIdx.value = newIdx;
    fetchCurAccountBook(newIdx);
  }
});
</script>

<template>
<div class="card account-book-card">
  <div class="account-book-title fs-5 mx-4 mt-3">
    {{ accountBookName }}
  </div>
  <div class="account-book-number fs-5 mx-4">
    {{ accountBookNumber }}
  </div>
  <div class="account-book-balance fs-4 mx-4">
    <span class="fw-bold">{{ accountBookBalance }}</span>
    <span class="fs-5">원</span>
  </div>
  <hr class="mx-4 my-3">
    <div class="btn-container mx-4 mb-3">
      <button class="btn btn-inquiry px-4" type="button">조회</button>
    </div>
  </div>
</template>

<style scoped>
hr {
  border-top: 3px dashed #60584C;
  height: 1px;
}
.account-book-card {
  background-color: #F1EEEA;
}
.account-book-balance {
  text-align: right;
}
.btn-container {
  text-align: right;
}
.btn-inquiry {
  background-color: #FFBC00;
}
</style>
