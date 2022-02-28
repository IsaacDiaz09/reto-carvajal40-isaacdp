import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import { getAuth } from "firebase/auth";
import Login from "@/views/Login.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/login",
    name: "Login",
    component: Login,
    meta: {
      requiredAuth: false
    }
  },
  {
    path: "/products-list",
    name: "Products",
    component: () =>
      import("@/views/ProductsList.vue"),
    meta: {
      requiredAuth: true
    }
  },
  {
    path: "/wish-list",
    name: "WishList",
    component: () =>
      import("@/views/WishList.vue"),
    meta: {
      requiredAuth: true
    }
  },
  {
    path: "/history",
    name: "history",
    component: () =>
      import("@/views/History.vue"),
    meta: {
      requiredAuth: true
    }
  }
  , {
    path: "/:pathMatch(.*)*",
    redirect: "/login"
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

// route guards
router.beforeEach((to, from, next) => {
  const user = getAuth().currentUser;
  const auth = to.matched.some(route => route.meta.requiredAuth);

  if (to.name !== 'Login' && auth && !user) next({ name: 'Login' })
  else next()
});

export default router;
