import axios from "axios";
import {redirect} from "react-router-dom";

const API_URL = 'http://localhost:8080/auth';

export async function auth(data) {
    return axios.post(`${API_URL}/authenticate`, {
        username: data.username,
        password: data.password
    });
}

export function isAuthLoader() {
    if (!localStorage.getItem("token")) {
        return redirect("/login")
    }
    return true
}

export function isAuth() {
    if (!localStorage.getItem("token")) {
        return false
    }
    return true
}

export function hasAuthority(authority) {
    if (localStorage.getItem('authority') !== authority) {
        return redirect("/home");
    }
}

export function getAuthority() {
    return localStorage.getItem("authority");
}

export function getId() {
    return localStorage.getItem("id");
}

export function generateRandomString(length) {
    let result = '';
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+-%';
    const charactersLength = characters.length;
    for (let i = 0; i < length; i++) {
        result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }
    return result;
}

