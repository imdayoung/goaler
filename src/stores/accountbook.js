import { defineStore } from 'pinia';
import apiInstance from './axios-instance';

export const useAccountBookInfoStore = defineStore('accountBook', {
    state: () => ({
      curAccountBook: {
        id: 0,
        title: "",
        accountName: "",
        accountNumber: "",
        balance: 0,
        goal: ""
      }
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
        } catch (err) {
          console.error(err);
        }
      },
      async getAccountBookInfo(accountBookId) {
        try {
          const res = await apiInstance.get(`/account-books/${accountBookId}`);
          
          this.curAccountBook.id = res.data.data.id;
          this.curAccountBook.title = res.data.data.title;
          this.curAccountBook.accountName = res.data.data.accountName;
          this.curAccountBook.accountNumber = res.data.data.accountNumber;
          this.curAccountBook.balance = res.data.data.balance;
          this.curAccountBook.goal = res.data.data.goal;
        } catch (err) {
          console.error(err);
        }
      }
    }
});
