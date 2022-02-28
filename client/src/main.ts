import { initializeApp } from "firebase/app";
import { createApp } from "vue";
import App from "./App.vue";
import router from "@/core/router/router";
import { store } from "@/core/store/store";
import { firebaseConfig } from "@/core/firebase";
import { getAuth } from "firebase/auth";

// Initialize Firebase
initializeApp(firebaseConfig);

getAuth().onAuthStateChanged((user): void => {
    if (user?.email) {
        store.dispatch("login", user.email);
    }
    if (router.currentRoute.value.name === 'Login' && user) {
        router.push({ name: "Products" })
    }
});

createApp(App).use(store).use(router).mount("#app");