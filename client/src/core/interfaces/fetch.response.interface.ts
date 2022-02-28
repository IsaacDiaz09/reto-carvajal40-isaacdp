import { History } from "@/core/interfaces/history.interface";
import { Product } from "@/core/interfaces/product.interface";

interface Pageable {
    sort: Sort;
    offset: number;
    pageNumber: number;
    pageSize: number;
    unpaged: boolean;
    paged: boolean;
}

interface Sort {
    empty: boolean;
    sorted: boolean;
    unsorted: boolean;
}

export interface FetchEntitiesResponse<T extends Product | History> {
    content: T[];
    pageable: Pageable;
    last: boolean;
    totalElements: number;
    totalPages: number;
    number: number;
    size: number;
    sort: Sort;
    first: boolean;
    numberOfElements: number;
    empty: boolean;
}

const defaultSort: Sort = {
    empty: true,
    sorted: false,
    unsorted: true,
}

const defaultPageable: Pageable = {
    sort: defaultSort,
    offset: -1,
    pageNumber: -1,
    pageSize: -1,
    unpaged: true,
    paged: false,
}

const baseFetchResponse = {
    content: [],
    pageable: defaultPageable,
    last: false,
    totalElements: 0,
    totalPages: 0,
    number: 0,
    size: 0,
    sort: defaultSort,
    first: false,
    numberOfElements: 0,
    empty: false
} 

export const defaultProductFetchResponse: FetchEntitiesResponse<Product> = {
    ...baseFetchResponse
}

export const defaultHistoryFetchResponse: FetchEntitiesResponse<History> = {
    ...baseFetchResponse
}