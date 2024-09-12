import Vuex from 'vuex'
import mainStore from "@/stores/main-store";
import userStore from "@/stores/user/user-store";

export const store = new Vuex.Store({
  modules: {
    mainStore,
    userStore
  }
})
