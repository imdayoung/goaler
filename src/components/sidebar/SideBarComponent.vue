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
    id: "aasdfasdf",
    title: "KB마이핏통장"
  },
  {
    id: "wersdfsdf",
    title: "KB모임통장 - 여행💛"
  }
])
</script>

<template>
  <div :class="['mt-3 sidebar', { show: props.isVisible }]">
    <div class="mx-2 my-2 fw-bold">
      내 통장
    </div>
    <button class="btn btn-account-book" type="button" v-for="accountBook in accountBooks">
      💰 {{ accountBook.title }}
    </button>
  </div>
</template>

<style scoped>
.sidebar {
  position: fixed;
  top: 0;
  left: 0;
  width: 180px;
  height: 80%;
  background-color: #E8E1D7;
  border-top-right-radius: 5%;
  border-bottom-right-radius: 5%;
  transform: translateX(-100%);
  transition: transform 0.3s ease;
}
.sidebar.show {
  transform: translateX(0);
}
.btn-account-book {
  width: 100%;
  text-align: left;
  font-size: small;
  /* background-color: saddlebrown; */
}
.btn-account-book:hover {
  background-color: #60584C;
  color: white;
}
</style>
