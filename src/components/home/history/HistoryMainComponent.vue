<script setup>
import AccountBookComponent from './AccountBookComponent.vue';
import HistoryComponent from './HistoryComponent.vue';
</script>

<template>
  <div class="container">
    <div class="card">
      <AccountBookComponent/>
      <HistoryComponent/>
    </div>
  </div>
</template>

<style scoped>
div {
  font-family: 'notoSansKR', 'notoSans';
  font-size: 0.8rem;
  color: #60584C;
}
</style>
