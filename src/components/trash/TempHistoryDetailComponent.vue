<script setup>
import Datepicker from 'vue3-datepicker';
import { useCategoryListStore } from '@/stores/categoryList';

const props = defineProps({
    history: {
        type: Object,
        required: true
    }
});

if (history) {
  history.date = new Date(history.date);
}

const categoryListStore = useCategoryListStore();

const emit = defineEmits(['close']);

const close = () => {
    emit('close');
}
</script>

<template>
  <div class="card detail-content">
    <button type="button" class="btn-close" aria-label="Close" @click="close"></button>

    <label for="category" class="mt-2">카테고리</label>
    <input type="text" id="category" v-model="history.category"/>

    <label for="amount" class="mt-2">금액</label>
    <input type="text" id="amount" v-model="history.amount"/>

    <label for="title" class="mt-2">내역</label>
    <input type="text" id="title" v-model="history.title"/>

    <label for="source" class="mt-2">자산</label>
    <input type="text" id="source" v-model="history.source"/>

    <label for="memo" class="mt-2">메모</label>
    <!-- <input type="text" id="memo" v-model="history.memo"/> -->
    <textarea id="memo" v-model="history.memo"></textarea>

    <div class="btn-container mt-3">
      <button class="btn btn-update" type="button">수정</button>
      <button class="btn btn-delete" type="button">삭제</button>
    </div>
  </div>
</template>

<style scoped>
.detail-content {
  background: white;
  padding: 20px;
  border-radius: 8px;
  position: absolute;
  top: 0;
  right: 0;
  z-index: 10;
  width: 40%;
  max-width: 300px; /* 필요한 경우 너비 조정 */
  height: 300px;
}
.btn-close {
  position: absolute;
  top: 10px;
  right: 10px;
}
.btn-container {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  width: 100%;
}
.btn-update, .btn-delete {
  flex: 1 1 calc(50% - 10px);
  text-align: center;
}
.btn-update {
  background-color: #FFBC00;
}
.btn-delete {
  background-color: #60584C;
}
input {
  height: 1.8rem;
}
textarea {
  height: 5rem;
  resize: none;
}
</style>
