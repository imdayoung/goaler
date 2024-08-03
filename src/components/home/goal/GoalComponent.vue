<script setup>
import { ref } from 'vue';
const goal = ref("10월까지 500만원 모으기");

if (goal.value == null) {
  goal.value = "아직 목표가 없어요!";
}
</script>

<template>
  <div class="card mx-3 my-3 py-3">
    <div class="container">
      <img src="/src/assets/bibi3.png"/>
      <div class="goal fs-3">
        {{ goal }}
      </div>
    </div>
    <button class="btn py-0">
      <span class="btn-goal">목표 설정하기</span>
    </button>
  </div>
</template>

<style scoped>
div {
  font-family: 'notoSansKr', 'notoSans';
  color: #60584C;
}
.card {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 80%;
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
