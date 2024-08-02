<template>
  <div>
    <div class="topbar">
      <button class="toggle-button" @click="toggleSidebar">
        ☰
      </button>
      <div class="topbar-content">
        <div class="title">MyApp</div>
        <nav class="nav">
          <button class="button" @click="showPeopleClick">
            People
          </button>
          <button class="button" @click="invitePeopleClick">
            Invite
          </button>
        </nav>
      </div>
    </div>

    <!-- 조건부로 렌더링된 모달 컴포넌트들 -->
    <InviteComponent v-if="currentModal === 'invite'" @close="closeModal" />
    <ShowPeopleComponent v-if="currentModal === 'people'" @close="closeModal" />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import InviteComponent from './InvitePeopleComponent.vue'; // InviteComponent.vue 임포트
import ShowPeopleComponent from './ShowPeopleComponent.vue';

const isSidebarOpen = ref(false);
const currentModal = ref(null); // 현재 열려 있는 모달 상태

function toggleSidebar() {
  isSidebarOpen.value = !isSidebarOpen.value;
  // 사이드바 열기/닫기 로직을 여기에 추가할 수 있습니다.
}

function showPeopleClick() {
  currentModal.value = currentModal.value === 'people' ? null : 'people'; // People 모달 전환
}

function invitePeopleClick() {
  currentModal.value = currentModal.value === 'invite' ? null : 'invite'; // Invite 모달 전환
}

function closeModal() {
  currentModal.value = null; // 모든 모달 닫기
}
</script>

<style scoped>
.topbar {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  background-color: rgb(255, 188, 0);
  padding: 10px 20px;
  display: flex;
  align-items: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  z-index: 1000; /* Ensure it's above other content */
}

.toggle-button {
  background: none;
  border: none;
  color: rgb(96, 88, 76);
  font-size: 24px;
  cursor: pointer;
  margin-right: 20px;
  transition: color 0.3s;
  width: 40px; /* 버튼의 너비 */
  height: 40px; /* 버튼의 높이 */
  display: flex;
  justify-content: center;
  align-items: center;
}

.toggle-button:hover {
  color: #ddd;
}

.topbar-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}

.title {
  font-size: 24px;
  font-weight: bold;
  color: rgb(96, 88, 76);
}

.nav {
  display: flex;
  align-items: center;
  margin-left: auto; /* 오른쪽 정렬 */
}

.button {
  background: none;
  border: none;
  color: rgb(96, 88, 76);
  font-size: 16px;
  cursor: pointer;
  margin-left: 20px;
  transition: color 0.3s;
}

.button:hover {
  color: #ddd;
}
</style>
