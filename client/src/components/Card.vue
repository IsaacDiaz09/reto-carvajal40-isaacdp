<template>
    <div class="col-sm-12 col-sm-6 col-md-4 col-lg-3 mt-1 mb-1">
        <div class="card rounded shadow shadow-sm h-100">
            <div class="card-header text-center">{{ product.name }}</div>
            <img
                :src="product.image"
                class="card-img-top rounded h-50"
                :alt="product.name"
                :title="product.description"
                @click="emitOnImgClick"
            />
            <div class="card-body">
                <div class="card-text">
                    <div class="text-center">
                        <div :id="`${product.name} details`">
                            <p>
                                Price:
                                <b>${{ product.price }}</b>
                            </p>
                            <p>
                                Quantity:
                                <b>{{ product.stock }}</b>
                            </p>
                            <div class="align-bottom">
                                <Button
                                    variant="primary"
                                    :small="true"
                                    text="Add to WishList"
                                    @click="emitAddToWishList"
                                    :disabled="isAddToWishBtnDisabled"
                                />
                                <Button
                                    variant="danger"
                                    :small="true"
                                    text="Reset stock"
                                    @click="emitResetStock"
                                    :disabled="!product.isAvailable"
                                />
                            </div>
                        </div>
                        <div v-if="!product.isAvailable" class="mt-2">
                            <p
                                class="text-white text-center border border-red border-1 bg-danger rounded pt-1 pb-1"
                            >Out of Stock!</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script lang="ts" setup>
import { Product } from "@/core/interfaces/product.interface"
import Button from "@/components/Button.vue"
import { computed } from "vue";

// props
interface Props {
    product: Product
}
const props = defineProps<Props>();
// emit
const emits = defineEmits(['onImageClick', 'addToWishList', 'resetStock']);
const emitOnImgClick = (): void => { emits('onImageClick') }
const emitAddToWishList = (): void => { emits('addToWishList') };
const emitResetStock = (): void => { emits('resetStock') };

// methods
const isAddToWishBtnDisabled = computed(() => !props.product.isAvailable);

</script>