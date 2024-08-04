import { defineStore } from 'pinia';
import apiInstance from './axios-instance';

export const useAccountBookInfoStore = defineStore('accountBook', {
    state: () => ({
      
    }),
    actions: {
      async getAccountBookMembers(accountBookId) {
        try {
          const res = await apiInstance.get(`/account-books/${accountBookId}/users`);
          return res.data.data;
        } catch (err) {
          console.error(err);
        }
      },
      async addAccountBookMember(accountBookId, email) {
        try {
          const res = await apiInstance.post(`/account-books/${accountBookId}/users`, {
            email: email
          });
          console.log(res.data);
        } catch (error) {
          console.error(error);
        }
      }
    }
});
