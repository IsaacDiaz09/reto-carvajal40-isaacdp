<template>
    <h2 class="text-center mt-2 bg-light">Lista de Deseos</h2>
    <WishList>
        <template v-slot:body>
            <WishListItem
                v-for="wish of wishes"
                :key="wish.idProd"
                :idItem="wish.idProd"
                :item="wish.name"
                @on-delete-wish="removeWish(wish)"
            />
        </template>
        <template v-slot:footer>
            <WishListEmptyFooter v-if="isWishListEmpty" />
            <WishListFooter v-if="!isWishListEmpty" />
        </template>
    </WishList>
</template>
<script lang="ts" setup>
import WishList from "@/components/WishListTable.vue";
import WishListEmptyFooter from "@/components/WishListEmpty.vue";
import WishListFooter from "@/components/WishListFooter.vue";
import WishListItem from "@/components/WishListItem.vue";
import { store } from '@/core/store/store';
import { computed } from "vue";
import { Product } from "@/core/interfaces/product.interface";
import { showToast } from "@/utils";
import { ToastVariant } from "@/core/enums/ToastVariant.enum";

// computed refs
const wishes = computed(() => store.state.wishList);
const isWishListEmpty = computed(() => store.state.wishList.length === 0);

// methods
const removeWish = (wish: Product): void => {
    store.dispatch("removeItemToWishList", wish);
    showToast(`El producto ${wish.name} se ha removido de su lista de deseos`, ToastVariant.WARNING);
}

</script>