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
    console.log("accountBookIdx:::", accountBookIdx.value);

    try {
      const response = await axios.get(`http://localhost:8080/api/v1/gpt/getAnalysis/${accountBookIdx.value}`);
      console.log("accountBookIdx:", accountBookIdx.value);
      
      console.log("Response data:", response.data);

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
  <div class="card my-3 py-3">
    <div class="analysis-title my-1">📢AI 금융 비서 비비가 알려드려요! </div>
    <div class="analysis">
      <div class="speech-bubble mx-5 my-3 px-3 py-3">
        <div v-for="(text, index) in splittedText" :key="index" class="text-white">
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
