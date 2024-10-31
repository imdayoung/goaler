import { defineStore } from 'pinia';
import apiInstance from './axios-instance';

export const useAccountHistory = defineStore('accountHistory', {
    state: () => ({
      accountHistories: [],
      accountExpenses: [],
      accountIncomes: []
    }),
    actions: {
      async getCurrentMonthExpenses(accountBookIdx) {
        try {
          const res = await apiInstance.get(`/account-books/${accountBookIdx}/history/expense`);
          this.accountExpenses = res.data.data;
        } catch (err) {
          console.error(err);
        }
      },
      async getCurrentMonthIncomes(accountBookIdx) {
        try {
            const res = await apiInstance.get(`/account-books/${accountBookIdx}/history/income`);
            this.accountIncomes = res.data.data;
        } catch (err) {
            console.error(err);
        }
      },
      async getAllHistories(accountBookIdx) {
        try {
          const res = await apiInstance.get(`/account-books/${accountBookIdx}/history`);
          this.accountHistories = res.data.data;
        } catch (err) {
          console.error(err);
        }
      }
    }
});
