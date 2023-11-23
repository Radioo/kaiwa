const notify = (title, text) => {
    const notification = new Notification(title, {
        body: text
    });

    setTimeout(() => {
        notification.close();
    }, 10 * 1000);

    notification.addEventListener('click', () => {
        window.open('http://localhost:8080/chat', '_blank');
    });
}

export default notify;
