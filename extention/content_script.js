let properties = {}
properties["remove_sidebar"] = true;
// properties["hide_comments"] = true;
console.log(properties)
// https://developer.mozilla.org/en-US/docs/Mozilla/Add-ons/WebExtensions/API/storage/StorageArea/get
// returns an array of an object which contains the keys
browser.storage.local.get(
  function(local_key){
    // Object.keys() returns an array of the objects properties
    Object.keys(properties).forEach(
      function(properties_key){
        console.log(local_key)
        console.log(properties_key)
        // ternary operator - value will equal whatever is left of the colon if T and whatever is right of the colon if F
        const value = local_key.hasOwnProperty(properties_key) ? local_key[properties_key] : properties[properties_key];
        // if remove_sidebar isn't there, then add it as an attribute within the html tag.
        if (!local_key.hasOwnProperty(properties_key)) browser.storage.local.set({ [properties_key] : value });
        document.documentElement.setAttribute(properties_key, value);
    });
});