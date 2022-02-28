<template>
    <div class="row">
        <div class="col-sm-8 col-md-6 col-lg-4 mx-auto d-table">
            <div class="card rounded shadow shadow-sm mt-5 mb-2">
                <div class="card-header">
                    <h3 class="mb-0 text-center">Login</h3>
                </div>
                <div class="card-body">
                    <form class="form" role="form" @submit.prevent="handleLoginSubmit">
                        <p
                            class="text-white text-center border border-red border-1 bg-danger bg-gradient rounded pt-1 pb-1"
                            v-if="showErrorMsg"
                            @click="showErrorMsg = !showErrorMsg"
                        >Credenciales erroneas</p>
                        <div class="form-group mt-1 mb-1">
                            <label for="email">Email</label>
                            <div class="input-group">
                                <input
                                    type="text"
                                    v-model="user.email"
                                    class="form-control form-control-lg rounded-0"
                                    id="email"
                                    placeholder="user@domain.com"
                                />
                            </div>
                        </div>
                        <div class="form-group mt-2 mb-1">
                            <label for="password">Contraseña</label>
                            <input
                                type="password"
                                v-model="user.password"
                                class="form-control form-control-lg rounded-0"
                                id="password"
                                placeholder="********"
                            />
                        </div>
                        <div class="d-flex justify-content-center mt-3">
                            <Button
                                type="submit"
                                variant="success"
                                style="width: 100%"
                                text="Iniciar sesion"
                                :disabled="!isValid"
                            />
                        </div>
                    </form>
                </div>
                <div class="card-footer text-center">
                    <span class="text-muted">Usa este usuario para iniciar sesion:</span>
                    <hr />jhondoe@mail.com
                    <br />super-secret
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts" setup>
import { getAuth, signInWithEmailAndPassword } from "firebase/auth";
import { Ref, ref, computed } from "vue";
import { User } from "@/core/interfaces/user.interface";
import Button from "@/components/Button.vue";
import router from "@/core/router/router";
import { store } from "@/core/store/store"
import { isEmptyOrNull } from "@/utils"

// refs
const user: Ref<User> = ref({ email: '', password: '' })
const showErrorMsg = ref(false);

// methods
const handleLoginSubmit = () => {
    const auth = getAuth();
    signInWithEmailAndPassword(auth, user.value.email, user.value.password)
        .then((userCredential) => {
            store.dispatch("login", userCredential.user.email);
            router.push('/products-list');
        })
        .catch((error) => {
            showErrorMsg.value = true;
            const errorCode = error.code;
            const errorMessage = error.message;
            console.error(errorCode);
            console.error(errorMessage);
        });
}

const isValid = computed(() => !isEmptyOrNull(user.value.email) && !isEmptyOrNull(user.value.password));

</script>

<style scoped src="@/views/shared.css">
</style>