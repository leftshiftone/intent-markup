const path = require('path');

module.exports = (env, argv) => ({
    entry: {
        'intent_markup' : './src/index.ts'
    },
    module: {
        rules: [
            {
                test: /\.ts?$/,
                use: 'ts-loader',
                exclude: /node_modules/,
            },
        ],
    },
    resolve: {
        extensions: ['.ts', '.js'],
    },
    output: {
        library:"IntentMarkup",
        libraryTarget: "umd",
        filename: 'libs/[name].min.js',
        path: path.resolve(__dirname, 'dist'),
    }
});
