interface BaseHttpResponse {
    message: string
    httpStatus: string
}

export interface HttpResponse extends BaseHttpResponse {
    statusCode: number,
}

export interface HttpErrorResponse extends BaseHttpResponse{
    errorCode: number
    timestamp: Date
}