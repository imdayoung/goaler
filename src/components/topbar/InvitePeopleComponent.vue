<template>
  <div class="bubble-overlay">
    <div class="bubble-content">
      <form @submit.prevent="handleSubmit">
        <div class="form-group">
          <input
            type="email"
            id="email"
            v-model="email"
            required
            placeholder="이메일을 입력해주세요."
          />
          <button type="submit">{{ buttonText }}</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const email = ref('');
const buttonText = ref('초대'); // 버튼의 기본 텍스트

function handleSubmit() {
  if (email.value) {
    console.log(`Sending invitation to ${email.value}`);


    // 유저-가계부 매핑 테이블에 유저 추가 로직 필요


    email.value = ''; // 입력 필드 비우기
    buttonText.value = '완료'; // 버튼 텍스트를 '성공!'으로 변경
    setTimeout(() => {
      buttonText.value = '초대'; // 일정 시간이 지나면 버튼 텍스트를 원래대로 복원
      $emit('close'); // 말풍선 닫기
    }, 2000); // 2초 후에 버튼 텍스트를 원래대로 복원
  }
}
</script>

<style scoped>
.bubble-overlay {
  position: fixed;
  top: 60px; /* 화면 상단에서 위치 조정 */
  right: 0px; /* 화면 오른쪽에서 위치 조정 */
  width: 350px; /* 말풍선의 너비를 조정하여 입력 필드가 충분히 보이도록 설정 */
  background: transparent; /* 배경을 투명하게 설정 */
  display: flex;
  justify-content: flex-end; /* 말풍선을 화면 오른쪽에 배치 */
  align-items: flex-start;
  z-index: 1000; /* 다른 요소들보다 위에 표시 */
}

.bubble-content {
  background: white;
  padding: 15px;
  border-radius: 8px;
  position: relative;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.form-group {
  display: flex;
  align-items: center; /* 입력 필드와 버튼을 수직 중앙에 맞춤 */
  gap: 10px; /* 입력 필드와 버튼 사이의 간격 */
}

input {
  flex: 1; /* 입력 필드가 가능한 공간을 차지하도록 설정 */
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
  min-width: 0; /* 입력 필드가 컨테이너의 너비에 맞추어 줄어들 수 있도록 설정 */
}

button {
  background-color: rgba(255, 187, 0, 0.908);
  border: none;
  color: white;
  padding: 10px 15px;
  border-radius: 4px;
  cursor: pointer;
  width: auto; /* 버튼 너비를 자동으로 설정 */
  transition: background-color 0.3s, transform 0.3s; /* 버튼 애니메이션 */
}

button:active {
  transform: scale(0.98); /* 버튼 클릭 시 약간의 클릭 효과 */
}
</style>
