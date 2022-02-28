import { createStore } from 'vuex'
import { Product } from '@/core/interfaces/product.interface';

export type State = {
  isUserLoggedIn: boolean,
  userLoggedInEmail: string,
  isLoading: boolean,
  showToast: boolean,
  toastMsg: string,
  toastVariant: string,
  wishList: Product[]
}

const state: State = {
  isUserLoggedIn: false,
  userLoggedInEmail: '',
  isLoading: false,
  showToast: false,
  toastMsg: '',
  toastVariant: 'dark',
  wishList: []
}

// store
export const store = createStore<State>({
  state,
  mutations: {
    setIsLoggedIn(state: State, payload: boolean): void {
      state.isUserLoggedIn = payload
    },
    setUserLoggedInEmail(state: State, payload: string): void {
      state.userLoggedInEmail = payload
    },
    setLoading(state: State, payload: boolean): void {
      state.isLoading = payload;
    },
    setToastVisible(state: State, payload: boolean): void {
      state.showToast = payload;
    },
    setToastMsg(state: State, payload: string): void {
      state.toastMsg = payload;
    },
    setToastVariant(state: State, payload: string): void {
      state.toastVariant = payload;
    },
    setWishList(state: State, payload: Product[]): void {
      state.wishList = payload
    },
  },
  actions: {
    addItemToWishList({ commit: Commit }, product: Product): void {
      // verificar si ya esta agregado
      const current: Product[] | Product = state.wishList.filter(current => current.idProd === product.idProd);
      const exist: boolean = current.length === 1;
      const clone: Product[] = [...state.wishList];
      // lo agrega solo si no esta ya agregado
      if (!exist) {
        clone.push(product);
        Commit('setWishList', clone);
      }
    },
    removeItemToWishList({ commit: Commit }, product: Product): void {
      const clone = [...state.wishList];
      const wishList = clone.filter((prod) => prod.idProd !== product.idProd);
      Commit('setWishList', wishList);
    },
    cleanWishList({ commit: Commit }): void {
      Commit('setWishList', []);
    },
    login({ commit: Commit }, email: string): void {
      Commit('setIsLoggedIn', true);
      Commit('setUserLoggedInEmail', email);
    },
    logout({ commit: Commit }): void {
      Commit('setIsLoggedIn', false);
      Commit('setUserLoggedInEmail', '');
    },
    setShowLoadingSpinner({ commit: Commit }, bool: boolean): void {
      Commit('setLoading', bool);
    },
    changeToastVariant({ commit: Commit }, variant: string): void {
      Commit('setToastVariant', variant);
    },
    showToast({ commit: Commit }, messageToast: string): void {
      Commit('setToastMsg', messageToast);
      Commit('setToastVisible', true);
      setTimeout(() => {
        Commit('setToastVisible', false);
        Commit('setToastMsg', '');
      }, 3000);
    }
  }
});