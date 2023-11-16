const swearFilter = (word) => {
    const swearWords = require('./data/swearWords.json').words

    for (let j in swearWords){
        let swear = swearWords[j]
        if(word.toLowerCase().includes(swear)){
            let censor = ""
            for (let i = 0; i < swear.length; i++)
                censor += "*"
            word = word.replace(new RegExp(swear, "ig"), censor)
        }
    }
    return word.toUpperCase();
}

export default swearFilter;
