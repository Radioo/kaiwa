import { createApp } from "vue";
import Chat from "../components/Chat.vue";
import Login from "../components/Login.vue";

createApp(Login).mount("#login");
createApp(Chat).mount("#chat");
