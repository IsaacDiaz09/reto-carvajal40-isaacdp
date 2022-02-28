<template>
    <tr>
        <th scope="row">{{ idItem }}</th>
        <td>{{ item }}</td>
        <td>
            <Button variant="danger" :small="true" @click="emitDeleteWish" text="Borrar" />
        </td>
    </tr>
</template>
<script lang='ts' setup>
import Button from "@/components/Button.vue"

defineProps({
    idItem: {
        type: Number,
        required: true
    },
    item: {
        type: String,
        required: true
    }
});

const emits = defineEmits(['onDeleteWish']);
const emitDeleteWish = (): void => { emits('onDeleteWish'); }

</script>