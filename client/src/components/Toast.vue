<template>
    <div
        id="toast"
        :class="{
            'show': showToast,
            danger: variant === 'danger',
            dark: variant === 'dark',
            success: variant === 'success',
            warning: variant === 'warning'
        }"
    >{{ text }}</div>
</template>
<script lang="ts" setup>
defineProps({
    variant: {
        type: String,
        default: "dark",
        required: true,
        validator: (value: string): boolean => ["dark", "success", "danger", "warning"].includes(value)
    },
    showToast: {
        type: Boolean,
        required: true,
        default: false
    },
    text: {
        type: String,
        required: true,
        default: ''
    }
});

</script>
<style>
#toast {
    visibility: hidden;
    min-width: 250px;
    margin-left: -125px;
    color: #fff;
    text-align: center;
    border-radius: 2px;
    padding: 16px;
    position: fixed;
    z-index: 999;
    left: 50%;
    bottom: 30px;
    font-size: 17px;
}

#toast.show {
    visibility: visible;
    -webkit-animation: fadein 0.5s, fadeout 0.5s 3s;
    animation: fadein 0.5s, fadeout 0.5s 3s;
}

@-webkit-keyframes fadein {
    from {
        bottom: 0;
        opacity: 0;
    }
    to {
        bottom: 30px;
        opacity: 1;
    }
}

@keyframes fadein {
    from {
        bottom: 0;
        opacity: 0;
    }
    to {
        bottom: 30px;
        opacity: 1;
    }
}

@-webkit-keyframes fadeout {
    from {
        bottom: 30px;
        opacity: 1;
    }
    to {
        bottom: 0;
        opacity: 0;
    }
}

@keyframes fadeout {
    from {
        bottom: 30px;
        opacity: 1;
    }
    to {
        bottom: 0;
        opacity: 0;
    }
}
</style>