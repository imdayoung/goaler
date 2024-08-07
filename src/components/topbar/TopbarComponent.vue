<template>
  <div>
    <div class="topbar">
      <button class="toggle-button" @mouseenter="showSidebar">
        ☰
      </button>
      <div class="topbar-content">
        <div class="title">{{ curAccountBookTitle }}</div>
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
    <SideBarComponent v-model:isVisible="isSidebarVisible"/>
    <InviteComponent v-if="currentModal === 'invite'" @close="closeModal" />
    <ShowPeopleComponent v-if="currentModal === 'people'" @close="closeModal" />
  </div>
</template>

<script setup>
import { ref, computed, onMounted, onUnmounted } from 'vue';
import InviteComponent from './InvitePeopleComponent.vue'; // InviteComponent.vue 임포트
import ShowPeopleComponent from './ShowPeopleComponent.vue';
import SideBarComponent from '../sidebar/SideBarComponent.vue';
import { useAccountBookInfoStore } from '@/stores/accountbook';

const accountBookStore = useAccountBookInfoStore();

const curAccountBookTitle = computed(() => accountBookStore.curAccountBook?.title || '');

const currentModal = ref(null); // 현재 열려 있는 모달 상태
const isSidebarVisible = ref(false);

const showSidebar = () => {
  isSidebarVisible.value = true;
}

const hideSidebar = () => {
  isSidebarVisible.value = false;
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

const handleClickOutside = (event) => {
  const sidebarElement = document.querySelector('.sidebar');
  if (sidebarElement && !sidebarElement.contains(event.target) && isSidebarVisible.value) {
    hideSidebar();
  }
}

onMounted(() => {
  document.addEventListener('click', handleClickOutside);
});

onUnmounted(() => {
  document.removeEventListener('click', handleClickOutside);
});

// 탑바 스크롤 동작 제어
let lastScrollY = window.scrollY;

const handleScroll = () => {
  if (window.scrollY > lastScrollY) {
    isSidebarVisible.value = false;
  }
  lastScrollY = window.scrollY;
}

onMounted(() => {
  window.addEventListener('scroll', handleScroll);
});

onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll);
});
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
  transition: transform 0.3s ease; /* 스크롤 시 부드럽게 이동하도록 트랜지션 추가 */
}

.topbar.hidden {
  transform: translateY(-100%); /* 숨길 때 탑바를 위로 이동 */
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
