<script setup>
import { watchEffect, ref, watch } from 'vue';

const props = defineProps({
  history: {
    type: Object,
    required: true
  },
  index: {
    type: Number,
    required: true
  },
  count: {
    type: Number,
    required: true
  }
});

const date = ref("");
const time = ref("");
const type = ref("");
const amount = ref(0);

watchEffect(() => {
  const [datePart, timePart] = props.history.created_at.split("T");
  const [year, month, day] = datePart.split("-");
  date.value = `${year}.${month}.${day}`;
  time.value = timePart;
  type.value = props.history.type === "INCOME" ? "입금" : "출금";
  amount.value = parseInt(props.history.amount, 10).toLocaleString();
});
</script>

<template>
  <div class="container fs-6 mt-2">
    <div>
      {{ date }} {{ time }}
    </div>
    <div>
      {{ history.title }}
    </div>
    <div class="fs-7">
      {{ history.category_name }}
    </div>
    <div class="amount fs-5 mb-2">
      {{ type }}
      <span class="fw-bold" :class="{'text-blue': history.type == 'INCOME', 'text-red': history.type == 'EXPENSE'}">
        {{ amount }}
      </span>
      <span>원</span>
    </div>
    <hr class="my-0" v-if="index < count - 1"/>
    
  </div>
</template>

<style scoped>
.fs-7 {
  font-size: 0.9rem;
}
.text-blue {
  color: #287EFF;
}
.text-red {
  color: #F23F3F;
}
.amount {
  text-align: right;
}
</style>
