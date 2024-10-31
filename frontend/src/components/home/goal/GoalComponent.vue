<script setup>
import { computed } from 'vue';

import { useAccountBookInfoStore } from '@/stores/accountbook';

const accountBookStore = useAccountBookInfoStore();

const curAccountBookGoal = computed(() => accountBookStore.curAccountBook?.goal || '');
</script>

<template>
  <div class="card my-3 py-3">
    <div class="container">
      <img src="/src/assets/bibi2.png"/>
      <div class="goal fs-3">
        {{ curAccountBookGoal }}
      </div>
    </div>
    <button class="btn py-0">
      <span class="btn-goal">목표 설정하기</span>
    </button>
  </div>
</template>

<style scoped>
.card {
  display: flex;
  justify-content: center;
  align-items: center;
}
.container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  gap: 10px;
}
.goal {
  font-family: 'jalnan';
  font-size: 0.8rem;
}
.btn-goal {
  font-size: small;

}
img {
  width: 2.5rem;
}
</style>
