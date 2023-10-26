let mix = require("laravel-mix");

mix.disableSuccessNotifications();

mix.setPublicPath("src/main/resources/static");

// mix.sass("src/main/resources/web-assets/main.scss", "css/app.css");

mix.js("src/main/resources/web-assets/main.js", "js/app.js")
    .vue({version: 3});
