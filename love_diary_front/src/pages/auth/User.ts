import Token from "@/pages/auth/Token";
import AppStorage from "@/pages/auth/AppStorage";

class User {

    responseAfterLogin(token,username) {
        if (token) {
            AppStorage.store(token, username);
        }

    }

    hasToken() {
        const token = localStorage.getItem("token");
        return !!token;
    }

    loggIn() {
        this.hasToken();
    }

    name() {

    }

    email() {
        if (this.loggIn()) {
            const payload = Token.payload(localStorage.getItem("token"));
            return payload.sub;
        }
        return false;
    }
}

export default User = new User();
