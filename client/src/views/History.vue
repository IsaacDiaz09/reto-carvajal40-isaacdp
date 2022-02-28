<template>
    <div v-if="existHistory">
        <div class="row mt-2 mb-2 bg-light">
            <div class="col-10">
                <h2 class="text-center">Historial Lista de Deseos</h2>
            </div>
            <div class="col-2 mt-1">
                <Button
                    variant="danger"
                    :small="true"
                    text="Limpiar historial"
                    @click="cleanHistory"
                />
            </div>
        </div>
        <div
            v-for="detail of history.content"
            :key="detail.idHistory"
            class="mt-1"
            :id="`history-${detail.idHistory}`"
            v-cloak
        >
            <h5>{{ detail.prod.name }}</h5>
            <h5 class="text-muted">{{ detail.at }}</h5>
            <hr />
        </div>
        <div id="history-paginator" class="mt-1">
            <Paginator
                @goback="fetchHistory(currentPage - 1)"
                @twice-goback="fetchHistory(currentPage - 2)"
                @twice-gonext="fetchHistory(currentPage + 2)"
                @gonext="fetchHistory(currentPage + 1)"
                :currentPage="currentPage"
                :totalPages="totalPages"
            />
        </div>
    </div>
    <div v-else>
        <h2 class="text-center text-danger bg-light">No hay registros que mostrar</h2>
        <router-link to="/products-list">Empiece a añadir sus deseos</router-link>
    </div>
</template>

<script lang="ts" setup>
import { defaultHistoryFetchResponse, FetchEntitiesResponse } from "@/core/interfaces/fetch.response.interface";
import Paginator from "@/components/Paginator.vue";
import { computed, ref } from "vue";
import http from "@/core/api/api.config";
import { History } from "@/core/interfaces/history.interface";
import { showToastOnErrorRequest } from "@/core/api/api.utils";
import { AxiosResponse } from "axios";
import { HttpErrorResponse } from "@/core/interfaces/http.response.interface";
import Button from "@/components/Button.vue"
import { showToast } from "@/utils";
import { ToastVariant } from "@/core/enums/ToastVariant.enum";
import { store } from "@/core/store/store";

// refs
const history = ref(defaultHistoryFetchResponse);
const currentPage = ref(1);
const totalPages = ref(-1);
const isLoading = computed(() => store.state.isLoading);
const existHistory = computed(() => !isLoading.value && history.value.content.length > 0);

// methods
const setHistory = (newHistory: FetchEntitiesResponse<History>): void => { history.value = newHistory }

const fetchHistory = async (page: number): Promise<void> => {
    await http.get<FetchEntitiesResponse<History>>(`/history?page=${page - 1}&size=5`)
        .then(({ data }) => {
            setHistory(data);
            currentPage.value = page;
            totalPages.value = data.totalPages;
        })
        .catch((error: AxiosResponse) => { showToastOnErrorRequest(error.data as HttpErrorResponse) })
}

const cleanHistory = async (): Promise<void> => {
    await http.delete<number>("/history/all")
        .then(({ status }) => {
            if (status === 204) {
                showToast("Se han eliminado todo el historico", ToastVariant.SUCCESS);
                fetchHistory(currentPage.value)
            } else {
                showToast("Ha sucedico un error inesperado", ToastVariant.DANGER);
            }
        });
}

// fetch history records at start component
(async (): Promise<void> => {
    await fetchHistory(1);
})()

</script>