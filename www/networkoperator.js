var networkoperator = {
    getOperator: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback, 
            'NetworkOperator', 
            'getOperator', 
            []
        ); 
    }
}
module.exports = networkoperator;
