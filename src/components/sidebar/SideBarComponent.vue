<template>
  <div :class="['sidebar', { show: props.isVisible }]">
    <div class="mx-3 mt-3 mb-2 fw-bold">
      내 통장
    </div>
    <router-link
      class="btn btn-account-book"
      v-for="accountBook in accountBooks"
      :key="accountBook.id"
      :to="`/account-books/${accountBook.id}`"
    >
      💰 {{ accountBook.title }}
    </router-link>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useMemberStore } from '@/stores/member';

const accountBooks = ref([]);
const memberStore = useMemberStore();

const props = defineProps({
  isVisible: {
    type: Boolean,
    default: false
  }
});

// todo: 로그인된 사용자 인덱스 전달
const fetchUserAccountBooks = async () => {
  const data = await memberStore.getAccountBooks(1);
  accountBooks.value = data.map(item => ({
    id: item.idx,
    title: item.account_name + " - " + item.title
  }));
}

onMounted(() => {
  fetchUserAccountBooks();
});
</script>

<style scoped>
.sidebar {
  position: fixed;
  top: 60px;
  left: -100px;
  width: 200px;
  height: 70%;
  background-color: #F7F7F5;
  box-shadow: 0px 0px 12px 5px #a1a1a1;
  border-top-right-radius: 5%;
  border-bottom-right-radius: 5%;
  transform: translateX(0);
  transform: translateX(-100%);
  transition: transform 0.3s ease, opacity 0.3s ease;
  opacity: 0;
  z-index: 900;
}
.sidebar.show {
  left: 0;
  transform: translateX(0);
  opacity: 1;
}
.btn-account-book {
  width: 100%;
  text-align: left;
  font-size: small;
}
.btn-account-book:hover {
  background-color: #60584C;
  color: white;
}
</style>
