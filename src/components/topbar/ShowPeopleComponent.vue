<template>
  <div class="bubble-overlay">
    <div class="bubble-content">
      <!-- 각 사용자 프로필을 반복하여 표시 -->
      <div v-for="(user, index) in userProfiles" :key="index" class="profile-card">
        <div class="profile-header">
          <img :src="user.photo" alt="Profile Photo" class="profile-photo"/>
          <div class="profile-info">
            <p>{{ user.name }}</p>
            <p><span class="email">{{ user.email }}</span></p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import { useRoute } from 'vue-router';
import { useAccountBookInfoStore } from '@/stores/accountbook';

const route = useRoute();
const accountBookIdx = ref(route.params.accountBookIdx);

const userProfiles = ref([]);

const userAccountBookInfoStore = useAccountBookInfoStore();

// 사용자 프로필 데이터를 가져오는 함수
const fetchUserProfiles = async () => {
  if (accountBookIdx.value) {
    const users = await userAccountBookInfoStore.getAccountBookMembers(accountBookIdx.value);
    userProfiles.value = users.map(user => ({
      photo: user.image || 'https://via.placeholder.com/50?text=User',
      name: user.name,
      email: user.email
    }));
  }
}

// 컴포넌트가 마운트되면 데이터 로드
onMounted(() => {
  fetchUserProfiles();
});

// URL의 accountBookIdx가 변경될 때마다 사용자 프로필을 다시 로드
watch(() => route.params.accountBookIdx, (newIdx) => {
  if (newIdx) {
    accountBookIdx.value = newIdx;
    fetchUserProfiles();
  }
});
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

.profile-header {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.profile-photo {
  width: 30px; /* 프로필 사진의 너비 */
  height: 30px; /* 프로필 사진의 높이 */
  border-radius: 50%; /* 사진을 동그랗게 만듭니다 */
  margin-right: 10px; /* 이름과 이메일 사이에 간격을 추가합니다 */
}

.profile-info {
  display: flex;
  flex-direction: column;
  font-size: 13px;
  margin-bottom: 0; /* margin-bottom 속성 제거 */
}

.profile-info p {
  margin: 0; /* 각 p 태그 사이의 간격을 제거 */
}

.email {
  color: gray;
  font-size: 12px;
}
</style>
