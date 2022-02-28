import axios, { AxiosInstance } from "axios";
import {
    AxiosRequestConfig,
    AxiosResponse,
    AxiosError,
    Axios,

} from "axios";
import { store } from "@/core/store/store";

const axiosInstance: AxiosInstance = axios.create({
    baseURL: "https://api-carvajal-reto.herokuapp.com/api",
    responseType: "json",
    responseEncoding: "utf-8",
    headers: {
        "Content-Type": "application/json",
        "Accept": "application/json",
    },
});

class Api extends Axios {
    private instance: AxiosInstance;
    private token: string;

    public constructor(axiosInstance: AxiosInstance) {
        super();
        this.token = "";
        this.instance = axiosInstance;
        this.instance.get = this.instance.get.bind(this);

        this.instance.interceptors.request.use(
            (config: AxiosRequestConfig) => {
                store.dispatch("setShowLoadingSpinner", true);
                return config;
            },
            (error: AxiosError) => {
                store.dispatch("setShowLoadingSpinner", false);
                if (error?.response?.status === 401) {
                    console.log('unauthorized, logging out ...');
                }
                return Promise.reject(error.response);
            });

        this.instance.interceptors.response.use(
            (response: AxiosResponse) => {
                store.dispatch("setShowLoadingSpinner", false);
                return response
            },
            (error: AxiosError) => {
                store.dispatch("setShowLoadingSpinner", false);
                if (error?.response?.status === 401) {
                    console.log('unauthorized, logging out ...');
                }
                return Promise.reject(error.response);
            })
    }

    public options<T, R = AxiosResponse<T>>(
        url: string,
        config?: AxiosRequestConfig
    ): Promise<R> {
        return this.instance.options(`${this.instance.defaults.baseURL}${url}`, config);
    }

    public get<T, R = AxiosResponse<T>>(
        url: string,
        config?: AxiosRequestConfig
    ): Promise<R> {
        return this.instance.get(`${this.instance.defaults.baseURL}${url}`, config);
    }

    public post<T, B, R = AxiosResponse<T>>(
        url: string,
        data: B,
        config?: AxiosRequestConfig
    ): Promise<R> {
        return this.instance.post(`${this.instance.defaults.baseURL}${url}`, data, config);
    }
    
    public put<T, B, R = AxiosResponse<T>>(
        url: string,
        data: B,
        config?: AxiosRequestConfig
    ): Promise<R> {
        return this.instance.put(`${this.instance.defaults.baseURL}${url}`, data, config);
    }

    public delete<T, R = AxiosResponse<T>>(
        url: string,
        config?: AxiosRequestConfig
    ): Promise<R> {
        return this.instance.delete(`${this.instance.defaults.baseURL}${url}`, config);
    }
}

export default new Api(axiosInstance);