export const getCircularReplacer = (v) => {
    const cache = new Map();
    return JSON.stringify(v, function(key, value) {
        if (typeof value === 'object' && value !== null) {
            if (cache.get(value)) {
                // Circular reference found, discard key
                return;
            }
            // Store value in our map
            cache.set(value, true);
        }
        return value;
    });
};
