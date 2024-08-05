<script setup>
import { ref, onMounted } from 'vue';
import { useAccountBookInfoStore } from '@/stores/accountbook';

const accountBookInfoStore = useAccountBookInfoStore();
const accountBookName = ref("");
const accountBookTitle = ref("");
const accountBookNumber = ref("");
const accountBookBalance = ref("");

const fetchCurAccountBook = async() => {
  await accountBookInfoStore.getAccountBookInfo(1);
  accountBookName.value = accountBookInfoStore.curAccountBook.accountName;
  accountBookTitle.value = accountBookInfoStore.curAccountBook.title;
  accountBookNumber.value = accountBookInfoStore.curAccountBook.accountNumber;
  accountBookBalance.value = accountBookInfoStore.curAccountBook.balance;
}

onMounted(() => {
  fetchCurAccountBook();
})
</script>

<template>
<div class="card account-book-card my-3 mx-3">
  <div class="account-book-title fs-5 mx-4 mt-3">
    {{ accountBookName }} - {{ accountBookTitle }}
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
