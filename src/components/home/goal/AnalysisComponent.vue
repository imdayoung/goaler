<script setup>
import { ref} from 'vue';

const text = ref("");
text.value = "[통장이름]은 하루 평균 [금액]원을 사용했어요! 이대로라면 [년/월/일] 뒤에 목표를 달성할 수 있어요. 목표 달성일을 앞당기고 싶으시다면 [카테고리] 의 소비를 줄여보세요!";
</script>

<template>
<div class="card">
  <div class="analysis-title mt-4 mx-3">📢AI 금융 비서 비비가 알려드려요! </div>
    <div class="speech-bubble mx-5 my-3 px-3 py-3">
      <div class="text-white">
        {{ text }}
      </div>
    </div>
    <div class="img-container my-3">
      <img src="/src/assets/bibi3.png"/>
    </div>
</div>
</template>

<style scoped>
.card {
  display: flex;
  justify-content: center;
  align-items: center;
}
.text-white {
  color: white;
}
.analysis-title {
  font-family: 'jalnan';
  font-weight: bold;
  text-align: center;
  font-size: 1.6rem;
}

.speech-bubble {
  position: relative;
  width: 80%;
  padding: 0px;
  background: #60584C;
  -webkit-border-radius: 20px;
  -moz-border-radius: 20px;
  border-radius: 20px;
}
.speech-bubble:after {
  content: '';
  position: absolute;
  border-style: solid;
  border-width: 20px 20px 0;
  border-color: #60584C transparent;
  display: block;
  width: 0;
  z-index: 1;
  bottom: -20px;
  left: 50%;
}
.img-container {
  position: relative;
  width: 25%;
  display: flex;
  justify-content: flex-end;
  align-items: flex-end;
}
img {
  width: 100%;
}
</style>
