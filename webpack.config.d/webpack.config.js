//上書きしないようassign
config.devServer = Object.assign(config.devServer, {
    historyApiFallback: true,
});