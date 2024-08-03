<script setup>
import { ref} from 'vue';

const text = ref("");
text.value = "[통장이름]은 하루 평균 [금액]원을 사용했어요! 이대로라면 [년/월/일] 뒤에 목표를 달성할 수 있어요. 목표 달성일을 앞당기고 싶으시다면 [카테고리] 의 소비를 줄여보세요!";
const splittedText = text.value.split(/[!?.]/);
</script>

<template>
<div class="card my-3 py-3">
  <div class="analysis-title my-1">📢AI 금융 비서 비비가 알려드려요! </div>
  <div class="analysis">
    <div class="speech-bubble mx-5 my-3 px-3 py-3">
      <div v-for="text in splittedText" class="text-white">
        {{ text }}
      </div>
    </div>
    <div class="img-container px-3">
      <img src="/src/assets/bibi3.png" class="mx-3"/>
    </div>
  </div>
</div>
</template>

<style scoped>
.text-white {
  color: white;
}
.analysis-title {
  font-family: 'jalnan';
  font-weight: bold;
  text-align: center;
  font-size: 1.8rem;
}
.analysis {
  display: flex;
  flex-direction: row;
  position: relative;
}
.speech-bubble {
	position: relative;
	background: #60584c;
	border-radius: 1em;
  width: 70%;
}
.speech-bubble:after {
	content: '';
	position: absolute;
	right: 0;
	top: 70%;
	width: 0;
	height: 0;
	border: 27px solid transparent;
	border-left-color: #60584c;
	border-right: 0;
	border-bottom: 0;
	margin-top: -13.5px;
	margin-right: -27px;
}
.img-container {
  position: relative;
  width: 30%;
  display: flex;
  justify-content: flex-end;
  align-items: flex-end;
}
img {
  max-width: 60%;
  max-height: 60%;
}
</style>
