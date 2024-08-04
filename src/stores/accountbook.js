import { defineStore } from 'pinia';
import apiInstance from './axios-instance';

export const useAccountBookInfoStore = defineStore('accountBook', {
    state: () => ({
      
    }),
    actions: {
      async getAccountBookMembers(id) {
        try {
          const res = await apiInstance.get(`/account-books/${id}/users`);
          return res.data.data;
        } catch (err) {
          console.error(err);
        }
      }
    }
});
