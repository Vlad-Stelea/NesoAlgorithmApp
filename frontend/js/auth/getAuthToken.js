function extractAuthToken() {
    const hashString = window.location.hash;
    return parseToken(hashString, "id_token");
}

function parseToken(hashString, tokenName) {
    // Add 1 to account for the equals sign
    const tokenIdx = hashString.indexOf(tokenName) + tokenName.length + 1;
    return hashString.substring(tokenIdx, hashString.indexOf('&', tokenIdx));
}
