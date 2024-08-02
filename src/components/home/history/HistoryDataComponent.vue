<script setup>
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

const date = props.history.dateTime.split(" ")[0].split("-");
const formattedDate = `${date[0]}.${date[1]}.${date[2]}`;
const time = props.history.dateTime.split(" ")[1].slice(0, -3);
const type = props.history.type === 1 ? "입금" : "출금";
const amount = parseInt(props.history.amount, 10).toLocaleString();
</script>

<template>
  <div class="container fs-6 mt-2">
    <div>
      {{ formattedDate }} {{ time }}
    </div>
    <div>
      {{ history.title }}
    </div>
    <div class="fs-7">
      {{ history.category }}
    </div>
    <div class="amount fs-5 mb-2">
      {{ type }}
      <span class="fw-bold" :class="{'text-blue': history.type == 1, 'text-red': history.type == 2}">
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
