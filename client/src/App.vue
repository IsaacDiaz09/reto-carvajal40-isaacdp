<template>
  <Spinner :isActive="isLoading" />
  <div id="nav">
    <p
      id="not-authenthicated"
      v-if="!isAuth"
      class="text-center fw-bold"
    >Saludos anonimo, inicia sesion para acceder</p>
    <p id="authenthicated" v-if="isAuth" class="text-center fw-bold">Bienvenido, {{ username }}</p>
    <div v-if="isAuth">
      <router-link to="/products-list">Products</router-link>&nbsp;|
      <router-link to="/wish-list">Wish List</router-link>&nbsp;|
      <router-link to="/history">History</router-link>&nbsp;|
      <a href="#" @click="logout">Logout</a>
    </div>
  </div>
  <div class="container">
     <Toast :showToast="toastIsVisible" :variant="toastVariant" :text="toastMessage" />
    <router-view />
  </div>
</template>
<script lang="ts" setup>
import { computed } from 'vue';
import { store } from '@/core/store/store';
import { getAuth } from "firebase/auth";
import router from "@/core/router/router";
import Spinner from "@/components/Spinner.vue";
import Toast from "@/components/Toast.vue";

// computed refs
const isAuth = computed(() => store.state.isUserLoggedIn);
const username = computed(() => store.state.userLoggedInEmail);

// logout
const logout = () => {
  getAuth().signOut().then((): void => {
    router.push('/login')
    store.dispatch("logout");
  })
}

// computed refs
const isLoading = computed(() => store.state.isLoading);
const toastIsVisible = computed(() => store.state.showToast);
const toastMessage = computed(() => store.state.toastMsg);
const toastVariant = computed(() => store.state.toastVariant);

</script>
<style src="@/styles.css">
</style>