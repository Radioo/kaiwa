const transformDate = (date) => {
    const then = new Date(date);
    const now = new Date();
    const offsetMs = now.getTimezoneOffset() * 60 * 1000;
    const ms = now.getTime() - then.getTime() + offsetMs;
    const sec = Math.floor(ms/1000)
    const min = Math.floor(sec/60)
    const hr = Math.floor(min/60)
    const day = Math.floor(hr/24)

    let output = day + " day" + (day==1?"":"s") + " ago"
    if (day == 0) output = hr + " hour" + (hr==1?"":"s") + " ago"
    if (hr == 0) output = min + " minute" + (min==1?"":"s") + " ago"
    if (min == 0) output = sec + " second" + (sec==1?"":"s") + " ago"

    return output
}

export default transformDate;
