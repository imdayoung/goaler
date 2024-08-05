import { defineStore } from 'pinia';
import apiInstance from './axios-instance';

export const useMemberStore = defineStore('member', {
    state: () => ({
      
    }),
    actions: {
      async getAccountBooks(memberId) {
        try {
          const res = await apiInstance.get(`members/${memberId}/account-books`);
          return res.data.data;
        } catch (err) {
          console.error(err);
        }
      }
    }
});
