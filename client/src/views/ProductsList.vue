<template>
    <div class="row mt-2 border border-light border-1 rounded" v-cloak>
        <Card
            v-for="product of products.content"
            :key="product.idProd"
            :product="product"
            @add-to-wish-list="add(product)"
            @reset-stock="reset(product)"
        />
        <div id="products-paginator" class="mt-1">
            <Paginator
                @goback="fetchProducts(currentPage - 1)"
                @twice-goback="fetchProducts(currentPage - 2)"
                @twice-gonext="fetchProducts(currentPage + 2)"
                @gonext="fetchProducts(currentPage + 1)"
                :currentPage="currentPage"
                :totalPages="totalPages"
            />
        </div>
    </div>
</template>

<script lang="ts" setup>
import { defaultProductFetchResponse, FetchEntitiesResponse } from "@/core/interfaces/fetch.response.interface";
import { HttpErrorResponse, HttpResponse } from "@/core/interfaces/http.response.interface";
import { Product } from "@/core/interfaces/product.interface";
import { History } from "@/core/interfaces/history.interface";
import { showToastOnErrorRequest, showToastOnSuccessfulRequest } from "@/core/api/api.utils";
import Paginator from "@/components/Paginator.vue";
import http from "@/core/api/api.config"
import Card from "@/components/Card.vue";
import { AxiosResponse } from "axios";
import { ref } from "vue";
import { store } from "@/core/store/store";
import { showToast } from "@/utils";
import { ToastVariant } from "@/core/enums/ToastVariant.enum";

// refs
const products = ref(defaultProductFetchResponse);
const currentPage = ref(1);
const totalPages = ref(-1);

// methods
const setProducts = (newProducts: FetchEntitiesResponse<Product>): void => { products.value = newProducts };

const fetchProducts = async (page: number): Promise<void> => {
    await http.get<FetchEntitiesResponse<Product>>(`/products?page=${page - 1}&size=8`).then(({ data }) => {
        setProducts(data);
        currentPage.value = page;
        totalPages.value = data.totalPages;
    }).catch((error: AxiosResponse) => { showToastOnErrorRequest(error.data as HttpErrorResponse) });
};

const add = async (product: Product): Promise<void> => {
    const history: History = {
        prod: product,
        at: new Date()
    };
    store.dispatch("addItemToWishList", product);
    await http.post<null, History>("/history", history)
        .then(() => showToast(`El producto ${product.name} se ha agregado a su lista de deseos`, ToastVariant.SUCCESS))
        .catch((error: AxiosResponse) => showToastOnErrorRequest(error.data as HttpErrorResponse));
};

const reset = async (product: Product): Promise<void> => {
    await http.put<HttpResponse, Product>("/product", product).then(({ data }) => {
        const fetchAfterReset = async (): Promise<void> => { await fetchProducts(currentPage.value) }
        showToastOnSuccessfulRequest(data, fetchAfterReset);
    }).catch((error: AxiosResponse) => { showToastOnErrorRequest(error.data as HttpErrorResponse) })
};

// fetch products at start component
(async () => {
    await fetchProducts(1);
})();
</script>