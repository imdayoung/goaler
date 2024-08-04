<template>
    <div>
      <!-- 챗봇 아이콘과 말풍선 -->
      <div class="chatbot-icon" @click="toggleChatWindow">
        <img src="/src/assets/bibi_white.png" alt="ChatBot Icon" />
        <div class="tooltip">도움이 필요하시면 저를 불러주세요!</div>
      </div>
      
      <!-- 챗봇 대화창 -->
      <ChatWindow v-if="isChatWindowOpen" @close="toggleChatWindow" />
    </div>
  </template>  
  
  <script setup>
  import { ref } from 'vue';
  import ChatWindow from './ChatWindowComponent.vue'; // ChatWindow.vue 임포트
  
  const isChatWindowOpen = ref(false);
  
  function toggleChatWindow() {
    isChatWindowOpen.value = !isChatWindowOpen.value;
  }
  </script>
  
<style scoped>
.chatbot-icon {
  position: fixed;
  bottom: 20px; /* 화면 하단에서의 거리 조정 */
  right: 20px; /* 화면 우측에서의 거리 조정 */
  cursor: pointer;
  z-index: 1000; /* 다른 요소들보다 위에 표시 */
}

.chatbot-icon img {
  width: 60px; /* 챗봇 아이콘의 너비 */
  height: 60px; /* 챗봇 아이콘의 높이 */
  border-radius: 50%; /* 동그란 형태로 만들기 */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3); /* 그림자 추가 */
}

.tooltip {
  position: absolute;
  right: 120%; /* 아이콘의 왼쪽에 위치하도록 조정 */
  top: 50%; /* 아이콘의 중앙에 맞추기 */
  transform: translateY(-50%); /* 중앙 정렬 보정 */
  background: #333333c0;
  color: white;
  padding: 8px;
  border-radius: 4px;
  font-size: 0.85em;
  white-space: nowrap; /* 텍스트가 줄 바꿈되지 않도록 설정 */
  visibility: hidden; /* 초기에는 보이지 않도록 설정 */
  opacity: 0;
  transition: opacity 0.3s ease;
  z-index: 1001; /* 아이콘보다 위에 표시 */
}

.tooltip::after {
  content: '';
  position: absolute;
  top: 50%;
  left: 100%; /* 툴팁의 오른쪽에 꼬리 위치 */
  transform: translateY(-50%); /* 수직 중앙 정렬 */
  border-width: 6px; /* 꼬리의 크기 조정 */
  border-style: solid;
  border-color: transparent transparent transparent #333333c0; /* 꼬리 색상과 방향 설정 */
}

.chatbot-icon:hover .tooltip {
  visibility: visible; /* 호버 시에 보이도록 설정 */
  opacity: 1;
}
</style>
