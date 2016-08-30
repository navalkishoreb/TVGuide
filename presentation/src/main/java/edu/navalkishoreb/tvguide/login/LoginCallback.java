package edu.navalkishoreb.tvguide.login;

import edu.navalkishoreb.tvguide.base.BaseRetry;

/**
 * Created by NavalB on 23-08-2016.
 */

interface LoginCallback extends
    BaseSocialLogin.Media,
    LoginApi.Callback,
    BaseRetry.Callback,
    BaseLogging.Callback {
}
