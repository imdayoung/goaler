<template>
  <div class="chat-window">
    <div class="chat-header">
      <h3>AI 금융 비서 비비</h3>
      <button @click="$emit('close')" class="close-button">✖</button>
    </div>
    <div class="chat-body">
      <div v-for="(message, index) in messages" :key="index" :class="{'message-bot': message.from === 'bot', 'message-user': message.from === 'user', 'message': true}">
        <img v-if="message.from === 'bot'" src="/src/assets/bibi.png" alt="Bot" class="message-photo"/>
        <div class="message-text-wrapper">
          <div class="message-text">
            {{ message.text }}
          </div>
          <div class="message-time">{{ message.time }}</div>
        </div>
      </div>
    </div>
    <div class="chat-footer">
      <input v-model="message" @keyup.enter="sendMessage" placeholder="AI 금융 비서 비비에게 궁금한 점을 물어보세요!" />
      <button @click="sendMessage">전송</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onUpdated } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';

const route = useRoute();
const accountBookIdx = ref(route.params.accountBookIdx); // 현재 route에서 accountBookIdx를 가져옵니다.
const message = ref('');
const messages = ref([
  { from: 'bot', text: '안녕하세요! 당신의 AI 금융 비서 비비입니다 :) 무엇을 도와드릴까요?', time: getCurrentTime() }
]);

function getCurrentTime() {
  const now = new Date();
  const hours = String(now.getHours()).padStart(2, '0');
  const minutes = String(now.getMinutes()).padStart(2, '0');
  return `${hours}:${minutes}`;
}

async function sendMessage() {
  const trimmedMessage = message.value.trim();
  if (trimmedMessage) {
    messages.value.push({ from: 'user', text: trimmedMessage, time: getCurrentTime() });
    message.value = ''; // 입력 필드 비우기

    try {
      // POST 요청 시 message와 accountBookIdx를 함께 전송
      const response = await axios.post('http://localhost:8080/api/v1/gpt/chat', {
        prompt: trimmedMessage,
        accountBookIdx: accountBookIdx.value
      });
      const botResponse = response.data;
      messages.value.push({ from: 'bot', text: botResponse, time: getCurrentTime() });
    } catch (error) {
      console.error("There was an error!", error);
      messages.value.push({ from: 'bot', text: 'An error occurred, please try again later.', time: getCurrentTime() });
    }
  }
}

onUpdated(() => {
  const chatBody = document.querySelector('.chat-body');
  chatBody.scrollTop = chatBody.scrollHeight;
});
</script>



<style scoped>
.chat-window {
  position: fixed;
  bottom: 20px;
  right: 20px;
  width: 60vh;
  height: 70vh;
  background: white;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  z-index: 1000;
}

.chat-header {
  background: rgb(96, 88, 76);
  color: white;
  padding: 12px;
  border-top-left-radius: 8px;
  border-top-right-radius: 8px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.chat-header h3 {
  font-size: 1.05em;
}

.close-button {
  background: none;
  border: none;
  color: white;
  font-size: 22px;
  cursor: pointer;
}

.chat-body {
  padding: 10px 10px 30px 10px;
  flex: 1;
  overflow-y: auto;
  height: calc(100% - 80px);
  display: flex;
  flex-direction: column;
  gap: 30px;
}

.message {
  display: flex;
  align-items: flex-start;
  max-width: 80%;
  position: relative;
}

.message-bot {
  justify-content: flex-start;
  margin-right: auto; /* 봇 메시지가 왼쪽으로 붙도록 설정 */
}

.message-user {
  justify-content: flex-end;
  margin-left: auto; /* 사용자 메시지가 오른쪽으로 붙도록 설정 */
}

.message-photo {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  margin-right: 10px;
}

.message-text-wrapper {
  display: flex;
  flex-direction: column;
  position: relative;
  max-width: 250px; /* 최대 너비 설정 */
}

.message-user .message-text-wrapper {
  flex-direction: row-reverse;
  align-items: flex-start;
}

.message-text {
  background: #f1f1f1;
  padding: 12px 16px;
  border-radius: 12px;
  word-break: break-word;
  position: relative;
}

.message-user .message-text {
  background: rgb(255, 204, 0);
  margin: 0;
  border-radius: 8px;
  max-width: 100%;
}

.message-time {
  font-size: 0.75em;
  color: #888;
  position: absolute;
  bottom: -20px; /* 말풍선 아래쪽에 위치하도록 조정 */
}

.message-bot .message-time {
  left: 0; /* 봇 메시지의 경우 왼쪽에 위치 */
}

.message-user .message-time {
  right: 0; /* 사용자 메시지의 경우 오른쪽에 위치 */
}

.chat-footer {
  display: flex;
  padding: 12px;
  border-top: 1px solid #ccc;
  background: white;
  align-items: center;
}

.chat-footer input {
  flex: 1;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-right: 10px;
  width: 80%;
}

.chat-footer input::placeholder {
  font-size: 0.8em; /* placeholder 글씨 크기 조정 */
}

.chat-footer button {
  padding: 12px;
  background: rgb(255, 204, 0);
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background 0.3s ease, transform 0.1s ease, box-shadow 0.3s ease;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  font-size: 0.9em;
}

.chat-footer button:hover {
  background: rgb(255, 230, 0); /* Hover 색상 */
}

.chat-footer button:active {
  background: rgb(255, 180, 0); /* 클릭 시 색상 */
  transform: scale(0.98); /* 클릭 시 버튼 크기 축소 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3); /* 클릭 시 그림자 효과 */
}
</style>
