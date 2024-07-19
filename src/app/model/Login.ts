export interface LoginResponse {
    message: string,
    code: number
}

export interface SignUpModel {
    email: string,
    first_name: string,
    last_name: string,
    phone_number: string,
    password: string, 
    enrolled_date: string
}

export interface signUpResponse {
    message: string,
    code: number,
    data: SignUpModel
}