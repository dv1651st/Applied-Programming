//1st arg is a callback function which is passed a details arg
//2nd is an array of strings, or url stings
//3rd is a extra spec option
browser.webRequest.onBeforeRequest.addListener( 
    async function(details) {
        const setting = await browser.local.get('redirect');
        if (setting['redirect']) return {redirectUrl};
    },
    {urls: "*//www.youtube.com/*"},
    // make the request synchronous, so you can cancel or redirect the request
    ["blocking"]
);
