<template>
  <div class="offcanvas offcanvas-start" tabindex="-1" ref="offcanvas">
    <div class="offcanvas-header">
      <h5 class="offcanvas-title">Sidebar</h5>
      <button type="button" class="btn-close" @click="closeSidebar"></button>
    </div>
    <div class="offcanvas-body">
      <!-- 사이드바의 내용 -->
      <p>Sidebar content goes here.</p>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import { Offcanvas } from 'bootstrap';

const offcanvas = ref(null);

const closeSidebar = () => {
  if (offcanvas.value) {
    const bsOffcanvas = Offcanvas.getOrCreateInstance(offcanvas.value);
    bsOffcanvas.hide();
    emit('close-sidebar');
  }
};

// Watch for changes in `offcanvas` ref to initialize Offcanvas
watch(offcanvas, (newEl) => {
  if (newEl) {
    const bsOffcanvas = new Offcanvas(newEl);
    bsOffcanvas.show();
  }
});
</script>

<style>
/* Add any additional styles if needed */
</style>


<style>
.offcanvas {
  transition: transform 0.3s ease-in-out;
}

.offcanvas-start {
  transform: translateX(-100%);
}

.offcanvas.show {
  transform: translateX(0);
}
</style>
