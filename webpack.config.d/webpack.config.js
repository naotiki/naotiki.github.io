if (config.devServer){
    //上書きしないようassign
    config.devServer = Object.assign(config.devServer, {
        historyApiFallback: true,
    });
}