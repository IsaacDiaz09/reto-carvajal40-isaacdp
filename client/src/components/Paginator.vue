<template>
  <div v-if="totalPages >= 0" class="d-flex justify-content-center">
    <Button
      variant="dark"
      text="<<"
      :small="true"
      style="margin-right: 5px;"
      @click="$emit('twice-goback')"
      :disabled="currentPage < 3"
    />
    <Button
      variant="dark"
      text="<"
      :small="true"
      style="margin-right: 10px;"
      @click="$emit('goback')"
      :disabled="currentPage === 1"
    />
    <span>
      Pagina
      <b>{{ currentPage }}</b> de
      <b>{{ totalPages }}</b>
    </span>
    <Button
      variant="dark"
      text=">"
      :small="true"
      style="margin-left: 10px;"
      @click="$emit('gonext')"
      :disabled="currentPage + 1 > totalPages"
    />
    <Button
      variant="dark"
      text=">>"
      :small="true"
      style="margin-left: 5px;"
      @click="$emit('twice-gonext')"
      :disabled="currentPage + 2 > totalPages"
    />
  </div>
</template>
<script lang="ts" setup>
import Button from "@/components/Button.vue";

// props
defineProps({
  currentPage: {
    type: Number,
    required: true
  },
  totalPages: {
    type: Number,
    required: true,
  }
});

</script>