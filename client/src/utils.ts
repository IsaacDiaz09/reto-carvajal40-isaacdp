import { ToastVariant } from "@/core/enums/ToastVariant.enum";
import { store } from "@/core/store/store";

export const showToast = (message: string, variant: ToastVariant): void => {
    store.dispatch("changeToastVariant", variant);
    store.dispatch("showToast", message);
};

export const closeFormModal = (callback: () => void): void => {
    store.dispatch("hideModal");
    callback();
};

export const clone = <T>(obj: T): T => JSON.parse(JSON.stringify(obj));

export const isEmptyOrNull = (value: string | null | undefined): boolean => (Object.is(value, null) || Object.is(value, undefined)) ? false : value?.trim().length === 0;