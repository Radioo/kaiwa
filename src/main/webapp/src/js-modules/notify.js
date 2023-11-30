const notify = (title, text) => {
    const notification = new Notification("Kaiwa", {
        body: title + ": " + text,
        timeout: 6000,
    });

    notification.addEventListener('click', () => {
        window.open('http://localhost:8080/chat', '_self');
    });
}

export default notify;
