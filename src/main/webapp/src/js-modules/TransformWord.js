const webProtocols = ["http://", "https://"];
const formats = [".apng", ".avif", ".bmp", ".cur", ".gif", ".ico", ".jpg", ".jpeg", ".jfif", ".pjpeg", ".pjp", ".png", ".svg", ".tif", ".tiff", ".webp"];

const transformWord = (word, row) => {
    for (let j in webProtocols) {
        let webProtocol = webProtocols[j]
        if (word.includes(webProtocol)){
            let isUsed = false;
            for (let i in formats) {
                let format = formats[i]
                if (word.includes(format) && !isUsed) {
                    isUsed = true
                    const image = document.createElement('img')
                    image.src = word
                    image.alt = "Image"
                    image.style = "max-height:200px;max-width:90%;"
                    setTimeout(function() {
                        document.getElementsByClassName("chat-message")[row].children[1].appendChild(image);
                    }, 0);
                }
            }
            if (!isUsed) {
                const link = document.createElement('a')
                link.href = word
                link.textContent = word
                link.style = "color:#888888"
                setTimeout(function() {
                    document.getElementsByClassName("chat-message")[row].children[1].appendChild(link);
                }, 0);
            }
            word = ""
        }
    }
    return word
}

export default transformWord;