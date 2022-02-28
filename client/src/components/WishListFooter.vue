<template>
    <th
        scope="row"
        colspan="2"
        class="text-danger"
    >Tienes {{ noProds }} productos en tu Lista de Deseos</th>
    <th scope="row" colspan="1">
        <Button :small="true" variant="danger" text="Borrar todo" @click="cleanWishList" />
    </th>
</template>
<script lang="ts" setup>
import { computed } from "vue";
import { store } from "@/core/store/store";
import Button from "@/components/Button.vue";
import { showToast } from "@/utils";
import { ToastVariant } from "@/core/enums/ToastVariant.enum";

// computed ref
const noProds = computed(() => store.state.wishList.length);

// clean wish list
const cleanWishList = (): void => {
    store.dispatch("cleanWishList");
    showToast("Se han borrado todos los productos de tu lista de deseos", ToastVariant.DANGER);
}

</script>