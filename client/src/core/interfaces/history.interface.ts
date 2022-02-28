import { Product } from "@/core/interfaces/product.interface";

export interface History {
    idHistory?: number,
    prod: Product,
    at: Date
}