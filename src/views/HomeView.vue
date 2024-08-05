<script setup>
import TopbarComponent from '@/components/topbar/TopbarComponent.vue'
import HomeComponent from '@/components/HomeComponent.vue'
import { useAccountBookInfoStore } from '@/stores/accountbook';
import { onMounted } from 'vue';

const accountBookStore = useAccountBookInfoStore();

// todo: 현재 보고있는 가계부 인덱스 전달
const fetchAccountBookInfo = async() => {
  await accountBookStore.getAccountBookInfo(1);
}

onMounted(() => {
  fetchAccountBookInfo();
});
</script>

<template>
  <TopbarComponent/>
  <HomeComponent/>
</template>

<style>
div {
  font-family: 'notoSansKr', 'notoSans';
  color: #60584C;
}
</style>
