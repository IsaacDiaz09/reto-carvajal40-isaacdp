<template>
  <button :type="type" :class="classStyles">{{ text }}</button>
</template>

<script lang="ts" setup>
import { ColorVariant } from "@/core/enums/ColorVariant.enum";
import { computed } from "vue";
// props
const props = defineProps({
  variant: {
    type: String,
    required: true,
    default: "mybtn primary",
  },
  text: {
    type: String,
    required: false,
    default: "",
  },
  type: {
    type: String as () => "button" | "submit" | "reset",
    default: String as () => "button",
    required: false,
  },
  small: {
    type: Boolean,
    default: false,
    required: false
  }
});
// refs
const classStyles = computed(() => getClassStyles(props.variant));
// methods
const getClassStyles = (variant: string): string => {
  let styles = "";
  switch (variant) {
    case ColorVariant.PRIMARY:
      styles = "mybtn primary";
      break;
    case ColorVariant.DANGER:
      styles = "mybtn danger";
      break;
    case ColorVariant.LIGHT:
      styles = "mybtn light";
      break;
    case ColorVariant.INFO:
      styles = "mybtn info";
      break;
    case ColorVariant.DARK:
      styles = "mybtn dark";
      break;
    case ColorVariant.WARNING:
      styles = "mybtn warning";
      break;
    case ColorVariant.SUCCESS:
      styles = "mybtn success";
      break;
    default:
      styles = "mybtn primary";
      break;
  }
  return props.small ? `${styles} small` : styles;
};
</script>

<style>
.mybtn {
  font-size: larger;
  font-weight: bolder;
  color: white;
  text-align: center;
  padding: 6px 13px 6px 13px;
  border-width: 2px;
  border-style: outset;
  border-color: #f3f6f4;
  min-height: 32px;
}
.small {
  padding: 2px 5px 2px 5px !important;
}
.mybtn:hover {
  cursor: pointer;
  box-shadow: 0 3px 3px #c9caca, 0 2px 2px #c9caca;
  transition: all 0.2s ease-in;
}
.mybtn:disabled {
  cursor: default !important;
}


</style>