export interface GradeResponse {
    message: string;
    code: number;
    data: Data
}

export interface Data {
    grade: number;
    price: number;
}