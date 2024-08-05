import { defineStore } from 'pinia';
import apiInstance from './axios-instance';

export const useAccountHistory = defineStore('accountHistory', {
    state: () => ({
      accountHistories: []
    }),
    actions: {
      async getAllHistories(accountBookIdx) {
        try {
          const res = await apiInstance.get(`/account-books/${accountBookIdx}/history`);
          this.accountHistories = res.data.data;
          console.log(this.accountHistories);
        } catch (err) {
          console.error(err);
        }
      }
    }
});
