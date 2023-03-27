class Token {

    isValidToken(token) {
        const payload = this.payload(token);
        if (!payload) {
            return false;
        }
        return true;
    }

    payload(token) {
        const payload = token.split(".");
        return this.decode(payload);
    }

    decode(payload) {
        return JSON.parse(atob(payload));
    }
}

export default Token = new Token();

