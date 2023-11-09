const swearFilter = (word) => {
    const swearWords = ["fuck", "ass", "shit", "bitch", "cock", "dick", "cunt", "fag", "pussy", "slut", "retard", "whore", "nigg", "cip", "pizd", "chuj", "jeb", "pierd", "cwel", "pedał", "pedal", "dziwk", "kurw", "mend", "dup", "gówn", "choler"]

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
