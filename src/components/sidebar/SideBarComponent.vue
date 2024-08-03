<script setup>
import { ref } from 'vue';

const props = defineProps({
  isVisible: {
    type: Boolean,
    default: false
  }
});

const accountBooks = ref([
  {
    id: "1",
    title: "KB마이핏통장"
  },
  {
    id: "2",
    title: "KB모임통장 - 여행💛"
  }
]);
</script>

<template>
  <div :class="['sidebar', { show: props.isVisible }]">
    <div class="mx-3 mt-3 mb-2 fw-bold">
      내 통장
    </div>
    <button class="btn btn-account-book" type="button" v-for="accountBook in accountBooks" :key="accountBook.id">
      💰 {{ accountBook.title }}
    </button>
  </div>
</template>

<style scoped>
.sidebar {
  position: fixed;
  top: 60px;
  left: -100px;
  width: 200px;
  height: 70%;
  background-color: #F7F7F5;
  box-shadow: 4px 7px 12px -1px gray;
  border-top-right-radius: 5%;
  border-bottom-right-radius: 5%;
  transform: translateX(0);
  transform: translateX(-100%);
  transition: transform 0.3s ease, opacity 0.3s ease;
  opacity: 0;
  z-index: 1100;
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
