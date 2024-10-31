import { defineStore } from 'pinia';
import apiInstance from './axios-instance';

export const useCategoryStore = defineStore('category', {
    state: () => ({
      categories: []
    }),
    actions: {
      async getCategories() {
        try {
          const res = await apiInstance.get('categories');
          this.categories = res.data.data.sort((a, b) => a.idx - b.idx);
        } catch (err) {
          console.error(err);
        }
      }
    }
});
