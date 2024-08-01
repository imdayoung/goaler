<script setup>

</script>

<template>
<div class="card account-book-card my-3 mx-3">
  <div class="account-book-title fs-5 mx-4 mt-3">
    KB모임통장
  </div>
  <div class="account-book-number fs-5 mx-4">
    880602-01-130351
  </div>      
  <div class="account-book-balance fs-4 mx-4">
    <span class="fw-bold">316,000</span>
    <span class="fs-5">원</span>
  </div>
  <hr class="mx-4 my-3">
    <div class="btn-container mx-4 mb-3">
      <button class="btn btn-inquiry px-4" type="button">조회</button>
    </div>
  </div>
</template>

<style scoped>
hr {
  border-top: 3px dashed #60584C;
  height: 1px;
}
.account-book-card {
  width: 30%;
  background-color: #F1EEEA;
}
.account-book-balance {
  text-align: right;
}
.btn-container {
  text-align: right;
}
.btn-inquiry {
  background-color: #FFBC00;
}
</style>
