import { HttpErrorResponse, HttpResponse } from "@/core/interfaces/http.response.interface";
import { ToastVariant } from "@/core/enums/ToastVariant.enum";
import { showToast } from "@/utils";

export const showToastOnSuccessfulRequest = (response: HttpResponse, callback?: () => void): void => {
    if (callback) {
        callback();
    }
    showToast(response.message, ToastVariant.SUCCESS);
};

export const showToastOnErrorRequest = (response: HttpErrorResponse, callback?: () => void): void => {
    if (callback) {
        callback();
    }
    showToast(response.message, ToastVariant.DANGER);
};