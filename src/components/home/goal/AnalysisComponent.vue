<script setup>
import { ref, onMounted, watch, computed } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';

const route = useRoute();
const accountBookIdx = ref(null); // 초기값을 null로 설정

const text = ref("");

// watch 훅을 사용하여 route.params.accountBookIdx가 변경될 때마다 실행
watch(() => route.params.accountBookIdx, async (newIdx) => {
  if (newIdx) {
    accountBookIdx.value = newIdx;
    text.value = ""; // clear text before fetching new data

    try {
      const response = await axios.get(`http://localhost:8080/api/v1/gpt/getAnalysis/${accountBookIdx.value}`);
      text.value = response.data;
    } catch (error) {
      console.error('Error fetching analysis:', error);
    }
  }
}, { immediate: true });

// 컴포넌트가 마운트될 때 현재 accountBookIdx를 설정
onMounted(() => {
  if (route.params.accountBookIdx) {
    accountBookIdx.value = route.params.accountBookIdx;
  }
});

const splittedText = computed(() => text.value.split(/[!?.]/));
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
  height: 100%;
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
  left: 45%;
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