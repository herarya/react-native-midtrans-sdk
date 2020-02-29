import { NativeModules } from 'react-native';

const { MidtransSdk } = NativeModules;

export default {
    checkOut: function (optionConect: ?object,
                        transRequest: ?object,
                        itemDetails: ?object,
                        creditCardOptions: ?object,
                        mapUserDetail: ?object,
                        optionColorTheme: ?object,
                        resultCheckOut) {
        MidtransSdk.checkOut(
            optionConect,
            transRequest,
            itemDetails,
            creditCardOptions,
            mapUserDetail,
            optionColorTheme,
            resultCheckOut);
    },
};
